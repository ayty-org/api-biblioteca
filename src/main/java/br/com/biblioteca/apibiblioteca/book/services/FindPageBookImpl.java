package br.com.biblioteca.apibiblioteca.book.services;

import br.com.biblioteca.apibiblioteca.book.Book;
import br.com.biblioteca.apibiblioteca.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FindPageBookImpl implements FindPageBook {
    private final BookRepository bookRepository;

    @Override
    public Page<Book> findPage() {
        PageRequest pageRequest = PageRequest.of(0, 24, Sort.Direction.valueOf("ASC"), "title");
        return bookRepository.findAll(pageRequest);
    }
}
