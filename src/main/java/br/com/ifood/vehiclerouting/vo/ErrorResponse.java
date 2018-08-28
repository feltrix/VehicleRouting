package br.com.ifood.vehiclerouting.vo;

import java.util.List;


public class ErrorResponse {

    private final String message;

    private final List<String> errors;

    public ErrorResponse (final String message, final List<String> errors) {
        this.message = message;
        this.errors = errors;
    }

    public String getMessage () {
        return message;
    }

    public List<String> getErrors () {
        return errors;
    }

}
