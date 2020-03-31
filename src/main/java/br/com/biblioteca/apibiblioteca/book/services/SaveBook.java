package br.com.biblioteca.apibiblioteca.book.services;

import br.com.biblioteca.apibiblioteca.book.Book;

@FunctionalInterface
public interface SaveBook {

    void insert(Book book);
}
