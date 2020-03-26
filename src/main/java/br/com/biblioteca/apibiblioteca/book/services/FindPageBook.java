package br.com.biblioteca.apibiblioteca.book.services;

import br.com.biblioteca.apibiblioteca.book.Book;
import org.springframework.data.domain.Page;

@FunctionalInterface
public interface FindPageBook {

    Page<Book> findPage();
}
