package br.com.petrim.lich.exception;

public class SecurityException extends RuntimeException {

    public SecurityException(String msg, Throwable t) {
        super(msg, t);
    }

}
