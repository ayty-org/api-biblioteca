package br.com.biblioteca.apibiblioteca.book.serveces;

import br.com.biblioteca.apibiblioteca.book.Book;

@FunctionalInterface
public interface GetBookService {

    public Book find(Long id);

}
