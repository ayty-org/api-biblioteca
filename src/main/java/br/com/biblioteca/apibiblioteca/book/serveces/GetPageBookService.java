package br.com.biblioteca.apibiblioteca.book.serveces;

import br.com.biblioteca.apibiblioteca.book.Book;
import org.springframework.data.domain.Page;

@FunctionalInterface
public interface GetPageBookService {

    public Page<Book> findPage(Integer page, Integer linesPerPage, String orderBy, String direction);
}
