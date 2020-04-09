package br.com.biblioteca.apibiblioteca.book.services;

import br.com.biblioteca.apibiblioteca.book.Book;

import java.util.List;

@FunctionalInterface
public interface ListBookService {

    List<Book> findAll();

}
