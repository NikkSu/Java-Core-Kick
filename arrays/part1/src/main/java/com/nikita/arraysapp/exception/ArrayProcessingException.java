package com.nikita.arraysapp.exception;

public class ArrayProcessingException extends Exception {

    public ArrayProcessingException() {
        super();
    }

    public ArrayProcessingException(String message) {
        super(message);
    }

    public ArrayProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArrayProcessingException(Throwable cause) {
        super(cause);
    }
}