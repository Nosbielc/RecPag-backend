package br.com.hackmovile.recpag.client.dto;

import java.io.Serializable;
import java.util.StringJoiner;

public class ExemploDto implements Serializable {

    private String exemplo;

    public ExemploDto(String exemplo) {
        this.exemplo = exemplo;
    }

    public String getExemplo() {
        return exemplo;
    }

    public void setExemplo(String exemplo) {
        this.exemplo = exemplo;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ExemploDto.class.getSimpleName() + "[", "]")
                .add("exemplo='" + exemplo + "'")
                .toString();
    }
}
