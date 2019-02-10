package br.com.hackmovile.recpag.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Response<T> implements Serializable {

    private T data;
    private List<String> errors;
    private HashMap<String, Object> extras;

    public Response() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<String> getErrors() {
        if (this.errors == null) {
            this.errors = new ArrayList<String>();
        }
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void addError(String error) {
        if (this.errors == null) {
            this.errors = new ArrayList<String>();
        }

        this.errors.add(error);
    }

    public void addExtra(String key, Object value) {
        if (this.extras == null) {
            this.extras = new HashMap<>();
        }

        this.extras.put(key, value);
    }

    public HashMap<String, Object> getExtras() {
        return extras;
    }

    public void setExtras(HashMap<String, Object> extras) {
        this.extras = extras;
    }
}