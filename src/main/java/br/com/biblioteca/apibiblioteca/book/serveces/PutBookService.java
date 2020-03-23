package br.com.biblioteca.apibiblioteca.book.serveces;

import br.com.biblioteca.apibiblioteca.book.Book;

@FunctionalInterface
public interface PutBookService {

    public void update(Book book);
}
