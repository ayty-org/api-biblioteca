package br.com.biblioteca.apibiblioteca.book.serveces;

import br.com.biblioteca.apibiblioteca.book.Book;

import java.util.List;

@FunctionalInterface
public interface GetAllBookService {

    public List<Book> findAll();

}
