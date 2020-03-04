package br.com.biblioteca.apibiblioteca.service;

import br.com.biblioteca.apibiblioteca.domain.Book;
import br.com.biblioteca.apibiblioteca.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private BookRepository bookRepository;

    public void instantiateTestDatabase() throws ParseException {

        Book book = new Book();

        bookRepository.saveAll(Arrays.asList(book));
    }
}
