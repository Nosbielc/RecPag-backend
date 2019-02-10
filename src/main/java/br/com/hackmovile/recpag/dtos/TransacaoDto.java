package br.com.hackmovile.recpag.dtos;

import br.com.hackmovile.recpag.enums.TransacaoStatus;
import br.com.hackmovile.recpag.enums.TransacaoTipo;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.StringJoiner;

public class TransacaoDto {

    @NotEmpty(message = "origem não pode ser null.")
    @Length(min = 10, max = 14, message = "origem pode ter no minimo 10 e maximo 14 caracteres.")
    private String origem;

    @NotEmpty(message = "destino não pode ser null.")
    @Length(min = 10, max = 14, message = "destino pode ter no minimo 10 e maximo 14 caracteres.")
    private String destino;

    @NotEmpty(message = "valor não pode ser null.")
    private String valor;

    private String keyTransacao;

    private TransacaoTipo transacaoTipo;

    private TransacaoStatus transacaoStatus;

    private String autenticador;

    public TransacaoDto() {
    }

    public TransacaoDto(String origem, String destino, String valor, String keyTransacao,
                        TransacaoTipo transacaoTipo, TransacaoStatus transacaoStatus,
                        String autenticador) {
        this.origem = origem;
        this.destino = destino;
        this.valor = valor;
        this.keyTransacao = keyTransacao;
        this.transacaoTipo = transacaoTipo;
        this.transacaoStatus = transacaoStatus;
        this.autenticador = autenticador;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TransacaoDto.class.getSimpleName() + "[", "]")
                .add("origem='" + origem + "'")
                .add("destino='" + destino + "'")
                .add("valor='" + valor + "'")
                .add("keyTransacao='" + keyTransacao + "'")
                .add("transacaoTipo=" + transacaoTipo)
                .add("transacaoStatus=" + transacaoStatus)
                .add("autenticador=" + autenticador)
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

    public TransacaoTipo getTransacaoTipo() {
        return transacaoTipo;
    }

    public void setTransacaoTipo(TransacaoTipo transacaoTipo) {
        this.transacaoTipo = transacaoTipo;
    }

    public TransacaoStatus getTransacaoStatus() {
        return transacaoStatus;
    }

    public void setTransacaoStatus(TransacaoStatus transacaoStatus) {
        this.transacaoStatus = transacaoStatus;
    }

    public String getAutenticador() {
        return autenticador;
    }

    public void setAutenticador(String autenticador) {
        this.autenticador = autenticador;
    }
}
