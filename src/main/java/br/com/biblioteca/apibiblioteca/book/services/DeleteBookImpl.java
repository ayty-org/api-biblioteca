package br.com.biblioteca.apibiblioteca.book.services;

import br.com.biblioteca.apibiblioteca.book.BookRepository;
import br.com.biblioteca.apibiblioteca.exceptions.BookNotDeletedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteBookImpl implements DeleteBook {

    private final BookRepository bookRepository;

    @Override
    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotDeletedException();
        }
        bookRepository.deleteById(id);
    }
}
