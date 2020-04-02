package br.com.biblioteca.apibiblioteca.book.services;

import br.com.biblioteca.apibiblioteca.book.Book;

@FunctionalInterface
public interface GetBook {

    Book find(Long id);

}
