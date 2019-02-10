package br.com.hackmovile.recpag.cliente.dto;

import java.io.Serializable;
import java.util.StringJoiner;

public class WavyDto implements Serializable {

    private String to;
    private String message;

    public WavyDto(String to, String message) {
        this.to = to;
        this.message = message;
    }

    public WavyDto() {
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", WavyDto.class.getSimpleName() + "[", "]")
                .add("to='" + to + "'")
                .add("message='" + message + "'")
                .toString();
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
