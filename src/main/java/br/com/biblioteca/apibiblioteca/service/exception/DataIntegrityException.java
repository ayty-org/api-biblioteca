package br.com.biblioteca.apibiblioteca.service.exception;

public class DataIntegrityException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DataIntegrityException(String mgs) {
        super(mgs);
    }

    public DataIntegrityException(String mgs,Throwable cause) {
        super(mgs,cause);
    }

}