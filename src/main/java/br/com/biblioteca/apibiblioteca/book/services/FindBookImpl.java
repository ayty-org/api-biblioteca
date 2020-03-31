package br.com.biblioteca.apibiblioteca.book.services;

import br.com.biblioteca.apibiblioteca.book.Book;
import br.com.biblioteca.apibiblioteca.book.BookRepository;
import br.com.biblioteca.apibiblioteca.exceptions.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FindBookImpl implements FindBook {

    private final BookRepository bookRepository;

    public Book find (Long id){
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

}
