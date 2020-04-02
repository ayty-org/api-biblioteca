package br.com.biblioteca.apibiblioteca.book.services;

import br.com.biblioteca.apibiblioteca.book.Book;
import br.com.biblioteca.apibiblioteca.book.BookRepository;
import br.com.biblioteca.apibiblioteca.exceptions.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateBookImpl implements UpdateBook {

    private final BookRepository bookRepository;

    @Override
    public void update(Book book, Long id) {
        if (bookRepository.findById(id).isPresent()){
            book.setId(id);
            bookRepository.save(book);
        }throw new BookNotFoundException();
    }
}
