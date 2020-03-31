package br.com.biblioteca.apibiblioteca.book.services;

import br.com.biblioteca.apibiblioteca.book.Book;
import br.com.biblioteca.apibiblioteca.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateBookImpl implements UpdateBook {

    private final BookRepository bookRepository;
    private final FindBookImpl findBook;

    @Override
    public void update(Book book) {
        Book newBook = findBook.find(book.getId());
        newBook.setId(book.getId());
        newBook.setTitle(book.getTitle());
        newBook.setResume(book.getResume());
        newBook.setIsbn(book.getIsbn());
        newBook.setAuthor(book.getAuthor());
        newBook.setYearBook(book.getYearBook());
        bookRepository.save(newBook);
    }
}
