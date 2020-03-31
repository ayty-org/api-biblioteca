package br.com.biblioteca.apibiblioteca.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class DataIntegrityException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DataIntegrityException(String mgs) {
        super(mgs);
    }

    public DataIntegrityException(String mgs, Throwable cause) {
        super(mgs, cause);
    }

}