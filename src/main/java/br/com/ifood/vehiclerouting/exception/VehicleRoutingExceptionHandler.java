package br.com.ifood.vehiclerouting.exception;

import br.com.ifood.vehiclerouting.vo.ErrorResponse;
import br.com.ifood.vehiclerouting.vo.ErrorResponseBuilder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class VehicleRoutingExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleRoutingExceptionHandler.class);
    private static final String INVALID_PARAMETERS = "Invalid Parameters";

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleDefaultException(final Exception exception) {
        log(exception);
        return new ErrorResponseBuilder()
                .message("Internal Error")
                .errors(Collections.singletonList(!StringUtils.isEmpty(exception.getMessage()) ? exception.getMessage()
                        : ""))
                .build();
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {
        log(exception);
        List<String> errors = new ArrayList<>();
        for (final FieldError error : exception.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + " " + error.getDefaultMessage());
        }

        return new ErrorResponseBuilder()
                .message(INVALID_PARAMETERS)
                .errors(errors)
                .build();
    }

    @ResponseBody
    @ExceptionHandler(value = ResourceNotFoundRuntimeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleResourceNotFoundRuntimeException(final ResourceNotFoundRuntimeException exception) {
        log(exception);

        return new ErrorResponseBuilder()
                .message(exception.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ErrorResponse handleHttpMediaTypeNotAcceptableException(final HttpMediaTypeNotAcceptableException exception) {
        log(exception);

        return new ErrorResponseBuilder()
                .message("Client Error")
                .build();
    }

    private void log(final Exception exception) {
        LOGGER.error(exception.getMessage(), exception);
    }

}
