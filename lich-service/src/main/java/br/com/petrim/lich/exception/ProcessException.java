package br.com.petrim.lich.exception;

public class ProcessException extends RuntimeException {

    public ProcessException(String message) {
        super(message);
    }

    public ProcessException(String message, Throwable t) {
        super(message, t);
    }

}
