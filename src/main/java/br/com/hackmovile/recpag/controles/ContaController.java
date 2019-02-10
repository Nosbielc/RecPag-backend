package br.com.hackmovile.recpag.controles;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/conta")
@CrossOrigin(origins = "*")
public class ContaController {

    @Value("${paginacao.qtd_por_pagina}")
    private int paginacao;



}
