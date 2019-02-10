package br.com.hackmovile.recpag.controles;

import br.com.hackmovile.recpag.cliente.IClienteZoop;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

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

    @Override
    @PostMapping("/pagamento")
    public ResponseEntity<Response<PagamentoDto>> pagamento(@Valid @RequestBody PagamentoDto pagamentoDto) {
        Response<PagamentoDto> response = new Response<>();

        Conta contaVendedor = contaServico.findByChave(pagamentoDto.getDestino());
        Conta contaComprador = contaServico.findByChave(pagamentoDto.getOrigem());

        //TODO Temos que verificar o saldo do pagador

        if (contaComprador != null && contaVendedor != null) {
            try {
                Float valor = Monetario.converteToBig(pagamentoDto.getValor()).floatValue();
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

            } catch (Exception e) {
                response.addError("Erro ao capturar valor do pagamento, favor repita a operacao");
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

        response.setData(null);

        return ResponseEntity.ok(response);
    }

}
