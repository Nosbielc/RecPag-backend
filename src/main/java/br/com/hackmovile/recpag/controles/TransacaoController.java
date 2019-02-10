package br.com.hackmovile.recpag.controles;

import br.com.hackmovile.recpag.controles.utils.ITransacaoController;
import br.com.hackmovile.recpag.dtos.PagamentoDto;
import br.com.hackmovile.recpag.dtos.RecebimentoDto;
import br.com.hackmovile.recpag.dtos.TransacaoDto;
import br.com.hackmovile.recpag.response.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/transacao")
@CrossOrigin(origins = "*")
public class TransacaoController implements ITransacaoController {

    @Value("${paginacao.qtd_por_pagina}")
    private int paginacao;

    @Override
    @PostMapping("/pagamento")
    public ResponseEntity<Response<PagamentoDto>> pagamento(@Valid @RequestBody PagamentoDto pagamentoDto) {
        Response<PagamentoDto> response = new Response<>();

        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping("/recebimento")
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
