package br.com.aluraflix.infraestructure.exception;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ServiceException extends RuntimeException {

    @Getter
    private List<String> messages;

    public ServiceException(String message, Exception e) {
        super(message, e);
        this.messages = Arrays.asList(message);
    }

    public ServiceException(String message) {
        super(message);
        this.messages = Arrays.asList(message);
    }

    public ServiceException(List<String> messages) {
        this.messages = messages;
    }

}

