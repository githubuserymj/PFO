package com.offer.exception;

public class NoneMessageException extends RuntimeException {


    public NoneMessageException() {
    }

    public NoneMessageException(String message) {
        super(message);
    }
}
