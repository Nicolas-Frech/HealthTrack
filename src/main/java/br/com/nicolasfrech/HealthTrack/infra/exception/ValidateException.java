package br.com.nicolasfrech.HealthTrack.infra.exception;

public class ValidateException extends RuntimeException {
    public ValidateException(String message) {
        super(message);
    }
}
