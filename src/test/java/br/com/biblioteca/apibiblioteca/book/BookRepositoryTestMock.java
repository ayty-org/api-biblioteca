package br.com.biblioteca.apibiblioteca.book;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.text.ParseException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DisplayName("Valida repositório de Book")
public class BookRepositoryTestMock {

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    public void setUp() throws ParseException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Book bookTest01 = new Book("teste title","teste resume","teste isbn","teste author",timestamp); //id=2
        this.bookRepository.save(bookTest01);
        Book bookTest02 = new Book("teste title 2","teste resume 2","teste isbn 2","teste author 2",timestamp); //id=3
        this.bookRepository.save(bookTest02);
    }

    @Test
    @DisplayName("Criando um novo Book")
    public void createBook(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Book bookTest03 = new Book("teste title 3","teste resume 3","teste isbn 3","teste author 3",timestamp); //id=4
        this.bookRepository.save(bookTest03);
        assertThat(bookTest03.getId()).isNotNull();
    }
}
