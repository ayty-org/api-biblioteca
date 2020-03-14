package br.com.biblioteca.apibiblioteca.Loan;

import br.com.biblioteca.apibiblioteca.domain.Book;
import br.com.biblioteca.apibiblioteca.domain.Loan;
import br.com.biblioteca.apibiblioteca.domain.User_app;
import br.com.biblioteca.apibiblioteca.repository.BookRepository;
import br.com.biblioteca.apibiblioteca.repository.User_appRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext
public class LoanRepository {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private User_appRepository userRepository;

    @Autowired
    private LoanRepository loanRepository;

    private static Date DATA;
    private static List<Book> books;

    @BeforeAll
    public void setUp() throws ParseException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        DATA = timestamp;

        User_app userTest = new User_app("teste nome",21,"46356357");

        Book bookTest = new Book("teste title","teste resume","teste isbn","teste author",DATA);
        books.add(bookTest);
        Loan loanTest = new Loan(userTest,bookTest);
        bookTest = this.bookRepository.save(bookTest);
    }

    @Test
    public void createBook(){
        Book bookTest01 = new Book("teste title","teste resume","teste isbn","teste author",DATA);
        bookTest01 = this.bookRepository.save(bookTest01);
        assertThat(bookTest01.getId()).isNotNull();
    }

    @Test
    public void getIdBook(){
        Optional<Book> bookTest02 = this.bookRepository.findById(2L);
        assertThat(bookTest02.isPresent()).isTrue();
        Book b2 = bookTest02.get();
        assertThat(b2.getId()).isNotNull();
        assertThat(b2.getTitle()).isEqualTo("teste title");
        assertThat(b2.getResume()).isEqualTo("teste resume");
        assertThat(b2.getIsbn()).isEqualTo("teste isbn");
        assertThat(b2.getAuthor()).isEqualTo("teste author");
        assertThat(b2.getYear_book()).isEqualTo(DATA);
    }

    @Test
    public void updateBook(){
        Book bookTest03 = new Book("teste title","teste resume","teste isbn","teste author",DATA);
        this.bookRepository.save(bookTest03);
        Optional<Book> bookTest04 = this.bookRepository.findById(3L);
        assertThat(bookTest04.isPresent()).isTrue();
        Book b3 = bookTest04.get();
        b3.setAuthor("Waldir");
        b3 = this.bookRepository.save(b3);
        assertThat(b3.getId()).isNotNull();
        assertThat(b3.getAuthor()).isEqualTo("Waldir");

    }

    @Test
    public void deleteBook(){
        Book bookTest05 = new Book("teste title","teste resume","teste isbn","teste author",DATA);
        bookTest05 = this.bookRepository.save(bookTest05);
        this.bookRepository.deleteById(bookTest05.getId());
        Optional<Book> b = this.bookRepository.findById(bookTest05.getId());
        assertThat(b.isPresent()).isFalse();
    }

}
