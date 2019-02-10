package br.com.hackmovile.recpag.dtos;

import br.com.hackmovile.recpag.enums.TransacaoStatus;
import br.com.hackmovile.recpag.enums.TransacaoTipo;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.StringJoiner;

public class RecebimentoDto extends TransacaoDto implements Serializable {

    @NotEmpty(message = "descricaoRecimento não pode ser null.")
    private String descricaoRecimento;

    public RecebimentoDto(String descricaoRecimento) {
        this.descricaoRecimento = descricaoRecimento;
    }

    public RecebimentoDto(String origem, String destino, String valor, String keyTransacao,
                          TransacaoTipo transacaoTipo, TransacaoStatus transacaoStatus, String descricaoRecimento) {
        super(origem, destino, valor, keyTransacao, transacaoTipo, transacaoStatus);
        this.descricaoRecimento = descricaoRecimento;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RecebimentoDto.class.getSimpleName() + "[", "]")
                .add("descricaoRecimento='" + descricaoRecimento + "'")
                .toString();
    }

    public String getDescricaoRecimento() {
        return descricaoRecimento;
    }

    public void setDescricaoRecimento(String descricaoRecimento) {
        this.descricaoRecimento = descricaoRecimento;
    }
}
