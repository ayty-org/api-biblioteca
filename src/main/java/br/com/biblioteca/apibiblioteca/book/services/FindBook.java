package br.com.biblioteca.apibiblioteca.book.services;

import br.com.biblioteca.apibiblioteca.book.Book;
import br.com.biblioteca.apibiblioteca.exceptions.LoanNotFoundException;

@FunctionalInterface
public interface FindBook {

    Book find(Long id) throws LoanNotFoundException;

}
