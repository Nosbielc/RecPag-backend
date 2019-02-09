package br.com.hackmovile.recpag.dtos;

import java.io.Serializable;
import java.util.StringJoiner;

public class PagamentoDto implements Serializable {

    private String origem;
    private String destino;
    private String valor;
    private String keyTransacao;

    public PagamentoDto() {
    }

    public PagamentoDto(String origem, String destino, String valor) {
        this.origem = origem;
        this.destino = destino;
        this.valor = valor;
    }

    public PagamentoDto(String origem, String destino, String valor, String keyTransacao) {
        this.origem = origem;
        this.destino = destino;
        this.valor = valor;
        this.keyTransacao = keyTransacao;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PagamentoDto.class.getSimpleName() + "[", "]")
                .add("origem='" + origem + "'")
                .add("destino='" + destino + "'")
                .add("valor='" + valor + "'")
                .add("keyTransacao='" + keyTransacao + "'")
                .toString();
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getKeyTransacao() {
        return keyTransacao;
    }

    public void setKeyTransacao(String keyTransacao) {
        this.keyTransacao = keyTransacao;
    }
}
