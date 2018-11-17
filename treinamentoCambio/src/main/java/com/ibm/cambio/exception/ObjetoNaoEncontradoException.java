package com.ibm.cambio.exception;

public class ObjetoNaoEncontradoException extends RuntimeException{

    private Integer code = 1;

    public ObjetoNaoEcontratoException(String message) {
        super(message);
    }

    public Integer getCode() {
        return code;
    }
}
