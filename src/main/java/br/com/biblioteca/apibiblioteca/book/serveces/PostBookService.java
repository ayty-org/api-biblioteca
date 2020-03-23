package br.com.biblioteca.apibiblioteca.book.serveces;

import br.com.biblioteca.apibiblioteca.book.Book;

@FunctionalInterface
public interface PostBookService {

    public void insert(Book book);
}
