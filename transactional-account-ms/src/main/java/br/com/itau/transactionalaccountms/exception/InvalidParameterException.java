package br.com.itau.transactionalaccountms.exception;

public class InvalidParameterException extends RuntimeException {

    public InvalidParameterException(String message) {
        super(message);
    }
}
