package br.com.ifood.vehiclerouting.vo;

import java.util.ArrayList;
import java.util.List;


public final class ErrorResponseBuilder {

    private String message;

    private List<String> errors;

    public ErrorResponseBuilder () {
        errors = new ArrayList<>();
    }

    public ErrorResponseBuilder message (String message) {
        this.message = message;
        return this;
    }

    public ErrorResponseBuilder errors (List<String> errors) {
        this.errors = errors;
        return this;
    }

    public ErrorResponse build () {
        return new ErrorResponse(message, errors);
    }
}
