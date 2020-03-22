package br.com.biblioteca.apibiblioteca.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ObjectNotFoundException(String mgs) {
        super(mgs);
    }

    public ObjectNotFoundException(String mgs,Throwable cause) {
        super(mgs,cause);
    }

}