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
    public void update(Book newBook, Long id) {
        Book book = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);

        book.setTitle(newBook.getTitle());
        book.setResume(newBook.getResume());
        book.setIsbn(newBook.getIsbn());
        book.setAuthor(newBook.getAuthor());
        book.setYearBook(newBook.getYearBook());
        bookRepository.save(book);
    }
}
