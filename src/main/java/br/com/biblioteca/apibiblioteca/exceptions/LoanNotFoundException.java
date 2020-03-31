package br.com.biblioteca.apibiblioteca.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LoanNotFoundException extends RuntimeException {

    public LoanNotFoundException() {
        super("Emprestimo não encontrado!");
    }
}
