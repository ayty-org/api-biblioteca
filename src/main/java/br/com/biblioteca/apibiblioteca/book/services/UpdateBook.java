package br.com.biblioteca.apibiblioteca.book.services;

import br.com.biblioteca.apibiblioteca.book.Book;

@FunctionalInterface
public interface UpdateBook {

    void update(Book book, Long id);
}
