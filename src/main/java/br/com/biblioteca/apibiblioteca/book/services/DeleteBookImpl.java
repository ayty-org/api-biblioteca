package br.com.biblioteca.apibiblioteca.book.services;

import br.com.biblioteca.apibiblioteca.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteBookImpl implements DeleteBook {

    private final BookRepository bookRepository;

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
