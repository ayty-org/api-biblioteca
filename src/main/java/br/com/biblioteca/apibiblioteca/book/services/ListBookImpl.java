package br.com.biblioteca.apibiblioteca.book.services;

import br.com.biblioteca.apibiblioteca.book.Book;
import br.com.biblioteca.apibiblioteca.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ListBookImpl implements ListBook {

    private final BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
