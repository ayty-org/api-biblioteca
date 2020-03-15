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
    public void setUp() throws ParseException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        DATA = timestamp;

        Book bookTest = new Book("teste title","teste resume","teste isbn","teste author",DATA);
        this.bookService.insert(bookTest);
    }

    @Test
    public void createBook(){
        Book bookTest01 = new Book("teste title","teste resume","teste isbn","teste author",DATA);
        this.bookService.insert(bookTest01);
        assertThat(bookTest01.getId()).isNotNull();
    }

    @Test
    public void getIdBook(){
        Book bookTest02 = this.bookService.find(2L);
        assertThat(bookTest02.getId()).isNotNull();
        assertThat(bookTest02.getTitle()).isEqualTo("teste title");
        assertThat(bookTest02.getResume()).isEqualTo("teste resume");
        assertThat(bookTest02.getIsbn()).isEqualTo("teste isbn");
        assertThat(bookTest02.getAuthor()).isEqualTo("teste author");
        assertThat(bookTest02.getYear_book()).isEqualTo(DATA);
    }

    @Test
    public void updateBook(){
        Book bookTest03 = new Book("teste title","teste resume","teste isbn","teste author",DATA);
        this.bookService.insert(bookTest03);
        Book bookTest04 = this.bookService.find(3L);
        bookTest04.setAuthor("Waldir");
        this.bookService.insert(bookTest04);
        assertThat(bookTest04.getId()).isNotNull();
        assertThat(bookTest04.getAuthor()).isEqualTo("Waldir");

    }

    @Test
    public void deleteBook(){
        Book bookTest05 = new Book("teste title","teste resume","teste isbn","teste author",DATA);
        this.bookService.insert(bookTest05);
        this.bookService.delete(bookTest05.getId());
        this.bookService.find(bookTest05.getId());
        assertThat(bookTest05.getId()).isNull();
    }

    /*@Test
    public void FindPageBook(){
        Pageable paging = (Pageable) PageRequest.of(1,10,Sort.by(Sort.Direction.fromString("ASC"),"id"));
        Page<Book> pages = (Page<Book>) this.bookRepository.findAll();
        assertThat(pages.getTotalElements()).isEqualTo(1);
    }
    */
}
