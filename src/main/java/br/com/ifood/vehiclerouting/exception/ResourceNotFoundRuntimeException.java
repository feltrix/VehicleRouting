package br.com.ifood.vehiclerouting.exception;

public class ResourceNotFoundRuntimeException extends RuntimeException{

    private String resource;
    private Long id;

    public ResourceNotFoundRuntimeException(String resource, Long id) {
        super(resource + " with id "+id+" not found");
        this.resource = resource;
        this.id = id;
    }
}
