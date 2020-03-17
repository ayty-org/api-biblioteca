package br.com.biblioteca.apibiblioteca.book;

import br.com.biblioteca.apibiblioteca.domain.Book;
import br.com.biblioteca.apibiblioteca.repository.BookRepository;
import br.com.biblioteca.apibiblioteca.service.BookService;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    private static Date DATA;

    @BeforeAll
    public void setUp(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        DATA = timestamp;

        Book bookTest01 = new Book("teste title","teste resume","teste isbn","teste author",DATA); //id=2
        this.bookService.insert(bookTest01);
        Book bookTest02 = new Book("teste title 2","teste resume 2","teste isbn 2","teste author 2",DATA); //id=3
        this.bookService.insert(bookTest02);
    }

    @Test
    public void createBook(){
        Book bookTest03 = new Book("teste title 3","teste resume 3","teste isbn 3","teste author 3",DATA); //id=4
        bookTest03 = this.bookService.insert(bookTest03);
        assertThat(bookTest03.getId()).isNotNull();
    }

    @Test
    public void getIdBook(){
        Book bookTest03 = this.bookService.find(2L);
        assertThat(bookTest03.getId()).isNotNull();
        assertThat(bookTest03.getTitle()).isEqualTo("teste title");
        assertThat(bookTest03.getResume()).isEqualTo("teste resume");
        assertThat(bookTest03.getIsbn()).isEqualTo("teste isbn");
        assertThat(bookTest03.getAuthor()).isEqualTo("teste author");
    }

    @Test
    public void updateBook(){
        Book book = this.bookService.find(3L);
        book.setAuthor("Waldir");
        book = this.bookService.update(book);
        assertThat(book.getId()).isNotNull();
        assertThat(book.getAuthor()).isEqualTo("Waldir");

    }

    @Test
    public void deleteBook(){
        Book bookTest05 = new Book("teste title 5","teste resume 5","teste isbn 5","teste author 5",DATA); //id=5
        bookTest05 = this.bookService.insert(bookTest05);
        this.bookService.delete(bookTest05.getId());
        bookTest05 = this.bookService.find(bookTest05.getId());
        assertThat(bookTest05).isNull();
    }

    /*@Test
    public void FindPageBook(){
        Pageable paging = (Pageable) PageRequest.of(1,10,Sort.by(Sort.Direction.fromString("ASC"),"id"));
        Page<Book> pages = (Page<Book>) this.bookRepository.findAll();
        assertThat(pages.getTotalElements()).isEqualTo(1);
    }
    */
}
