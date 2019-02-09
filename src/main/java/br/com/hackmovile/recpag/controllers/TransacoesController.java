package br.com.hackmovile.recpag.controllers;

import br.com.hackmovile.recpag.dtos.PagamentoDto;
import br.com.hackmovile.recpag.response.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transacoes")
@CrossOrigin(origins = "*")
public class TransacoesController {

    @Value("${pagination.qtd_by_page}")
    private int fetchForPage;


    @GetMapping
    public ResponseEntity<Response<PagamentoDto>> pagamento(@RequestBody PagamentoDto pagamentoDto) throws Exception {
        Response<PagamentoDto> response;

        ResponseEntity.ok();
    }

}
