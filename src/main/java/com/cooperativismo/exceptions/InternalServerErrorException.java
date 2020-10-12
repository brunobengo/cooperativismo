package com.cooperativismo.exceptions;

public class InternalServerErrorException extends RuntimeException {

    private static final long serialVersionUID = 3244236110056980710L;

    public InternalServerErrorException(String message) {
        super(message);
    }
}
