package br.com.hackmovile.recpag.dtos;

import br.com.hackmovile.recpag.enums.TransacaoStatus;
import br.com.hackmovile.recpag.enums.TransacaoTipo;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.StringJoiner;

public class PagamentoDto extends TransacaoDto implements Serializable {

    @NotEmpty(message = "descricao não pode ser null.")
    private String descricao;

    public PagamentoDto(String descricao) {
        this.descricao = descricao;
    }

    public PagamentoDto(String origem, String destino, String valor, String keyTransacao,
                        TransacaoTipo transacaoTipo, TransacaoStatus transacaoStatus, String descricao, String autenticador) {
        super(origem, destino, valor, keyTransacao, transacaoTipo, transacaoStatus, autenticador);
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PagamentoDto.class.getSimpleName() + "[", "]")
                .add("descricao='" + descricao + "'")
                .toString();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
