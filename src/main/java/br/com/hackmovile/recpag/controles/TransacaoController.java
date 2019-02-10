package br.com.hackmovile.recpag.controles;

import br.com.hackmovile.recpag.cliente.IClienteWavy;
import br.com.hackmovile.recpag.cliente.dto.WavyDto;
import br.com.hackmovile.recpag.controles.utils.ITransacaoController;
import br.com.hackmovile.recpag.dtos.PagamentoDto;
import br.com.hackmovile.recpag.dtos.RecebimentoDto;
import br.com.hackmovile.recpag.dtos.TransacaoDto;
import br.com.hackmovile.recpag.entidades.Conta;
import br.com.hackmovile.recpag.entidades.Transacao;
import br.com.hackmovile.recpag.enums.TransacaoStatus;
import br.com.hackmovile.recpag.enums.TransacaoTipo;
import br.com.hackmovile.recpag.response.Response;
import br.com.hackmovile.recpag.servicos.IContaServico;
import br.com.hackmovile.recpag.servicos.ITransacaoServico;
import br.com.hackmovile.recpag.utils.ChaveSecreta;
import br.com.hackmovile.recpag.utils.Monetario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/transacao")
@CrossOrigin(origins = "*")
public class TransacaoController implements ITransacaoController {

    @Value("${paginacao.qtd_por_pagina}")
    private int paginacao;

    @Autowired
    IContaServico contaServico;

    @Autowired
    ITransacaoServico transacaoServico;

    @Autowired
    RestTemplate restTemplate;

    @Override
    @PostMapping("/pagamento")
    public ResponseEntity<Response<PagamentoDto>> pagamento(@Valid @RequestBody PagamentoDto pagamentoDto) {
        Response<PagamentoDto> response = new Response<>();

        Conta contaVendedor = contaServico.findByChave(pagamentoDto.getDestino());
        Conta contaComprador = contaServico.findByChave(pagamentoDto.getOrigem());

        //TODO Temos que verificar o saldo do pagador
        if (contaComprador != null && contaVendedor != null &&
                !(pagamentoDto.getDestino().trim().equalsIgnoreCase(pagamentoDto.getOrigem().trim()))) {
            try {
                Float valor = Monetario.converteToBig(pagamentoDto.getValor()).floatValue();
                Float saldo = transacaoServico.saldoConta(contaComprador.getId());

                if (saldo != null && saldo < valor) {
                    response.addError(String.format("Sem saldo suficiente para realizar a transacao.", saldo));
                }

                String autenticador = ChaveSecreta.getHashTransacao(
                        pagamentoDto.getOrigem(),
                        pagamentoDto.getDestino(),
                        pagamentoDto.getDescricao(),
                        String.valueOf(System.currentTimeMillis()));

                pagamentoDto.setAutenticador(autenticador);
                pagamentoDto.setTransacaoStatus(TransacaoStatus.PROCESSAMENTO_CONCLUIDO);
                pagamentoDto.setTransacaoTipo(TransacaoTipo.CREDITO);

                transacaoServico.persist(new Transacao(valor, pagamentoDto.getDescricao(),
                        pagamentoDto.getAutenticador(), new Date(), contaComprador,
                       contaVendedor, pagamentoDto.getTransacaoStatus(), pagamentoDto.getTransacaoTipo()));

                response.setData(pagamentoDto);

                sendSms(contaVendedor, "Venda Realizada com sucesso !!!");
                sendSms(contaComprador, "Compra Realizada com sucesso ;) ");
            } catch (Exception e) {
                response.addError("Erro ao capturar valor do pagamento, favor repita a operacao! :(");
            }
        }

        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping("/recebimento")
    @Deprecated
    public ResponseEntity<Response<RecebimentoDto>> recebimento(@Valid @RequestBody RecebimentoDto recebimentoDto) {
        Response<RecebimentoDto> response = new Response<>();

        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("/listar")
    public ResponseEntity<Response<Page<TransacaoDto>>> listar(@RequestParam(value = "pag", defaultValue = "0") Integer pag,
                                                           @RequestParam(value = "ord", defaultValue = "id") String ord,
                                                           @RequestParam(value = "dir", defaultValue = "DESC") String dir,
                                                           @RequestParam(value = "conta", defaultValue = "") String conta) {
        Response<Page<TransacaoDto>> response = new Response<>();
        PageRequest pageRequest = PageRequest.of(pag, this.paginacao, Sort.Direction.valueOf(dir), ord);
        Page<Transacao> transacao = null;

        if (!conta.equalsIgnoreCase("")) {
            Conta contaFiltro = contaServico.findByChave(conta);

            if (contaFiltro != null) {

                transacao = transacaoServico.findAllByOrigemOrDestinoOrderByIdAsc(
                        contaFiltro, contaFiltro, pageRequest);

                Page<TransacaoDto> transacaoDtos = transacao.map(
                        trans -> toTransacaoDto(trans)
                );

                response.addExtra("saldo", transacaoServico.saldoConta(contaFiltro.getId()) );
                response.setData(transacaoDtos);
            }

        }

        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<HashMap<String, Object>> listarTransacoes(@RequestParam(value = "pag", defaultValue = "0") Integer pag,
                                                                    @RequestParam(value = "ord", defaultValue = "id") String ord,
                                                                    @RequestParam(value = "dir", defaultValue = "DESC") String dir,
                                                                    @RequestParam(value = "conta", defaultValue = "") String conta) {
        HashMap<String, Object> map = new HashMap<>();

        if (!conta.equalsIgnoreCase("")) {
            Conta contaFiltro = contaServico.findByChave(conta);

            if (contaFiltro != null) {

                map.put("transacoes", transacaoServico.findAllByOrigemOrDestinoOrderByIdAsc(contaFiltro, contaFiltro));

                Float saldo = transacaoServico.saldoConta(contaFiltro.getId());
                DecimalFormat df = new DecimalFormat("########.##");
                df.setRoundingMode(RoundingMode.DOWN);

                map.put("saldo", saldo == null ? 0.00 : df.format(saldo));
            }
        }

        return ResponseEntity.ok(map);
    }

    private TransacaoDto toTransacaoDto(Transacao transacao) {
        return new TransacaoDto(transacao.getOrigem().getNome(),
                transacao.getDestino().getNome(),
                transacao.getValorTransacao().toString(), transacao.getAutenticador(),
                transacao.getTransacaoTipo(), transacao.getTransacaoStatus(),
                transacao.getAutenticador());
    }

    private void sendSms(Conta conta, String msg) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Access-key", "ek-12548965369");
            HttpEntity<WavyDto> entity = new HttpEntity<WavyDto>(new WavyDto(conta.getTelefone(), msg),headers);

             restTemplate.exchange(
                    "http://messaging-api.wavy.global:8080/v1/sms/send", HttpMethod.POST, entity, String.class).getBody();
        } catch (Exception ex) {}

    }

}
