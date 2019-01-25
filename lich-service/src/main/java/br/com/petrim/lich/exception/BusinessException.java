package br.com.petrim.lich.exception;

import br.com.petrim.lich.util.MessageUtil;

public class BusinessException extends RuntimeException {

    private String message;
    private Object[] params;

    public BusinessException(String msg, Throwable e, Object...params) {
        super(e);
        this.params = params;
        this.message = MessageUtil.getMessage(msg, params);
    }

    public BusinessException(String msg, Object...params) {
        this.params = params;
        this.message = MessageUtil.getMessage(msg, params);
    }

    public Object[] getParams() {
        return params;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
