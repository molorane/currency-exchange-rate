package com.currencyexchangerate.exchangeservice2.exception;

import lombok.Getter;

public class DataNotFoundException extends RuntimeException{

    @Getter
    private final String message;

    public DataNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public DataNotFoundException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }
}
