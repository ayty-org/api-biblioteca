package br.com.biblioteca.apibiblioteca.book;

import br.com.biblioteca.apibiblioteca.domain.Book;
import br.com.biblioteca.apibiblioteca.repository.BookRepository;
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
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    private static Date DATA;

    @BeforeAll
    public void setUp() throws ParseException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        DATA = timestamp;

        Book bookTest01 = new Book("teste title","teste resume","teste isbn","teste author",DATA); //id=2
        this.bookRepository.save(bookTest01);
        Book bookTest02 = new Book("teste title 2","teste resume 2","teste isbn 2","teste author 2",DATA); //id=3
        this.bookRepository.save(bookTest02);
    }

    @Test
    public void createBook(){
        Book bookTest03 = new Book("teste title 3","teste resume 3","teste isbn 3","teste author 3",DATA); //id=4
        bookTest03 = this.bookRepository.save(bookTest03);
        assertThat(bookTest03.getId()).isNotNull();
    }

    @Test
    public void getIdBook(){
        Optional<Book> bookTest04 = this.bookRepository.findById(2L); //pega livro por id 2
        assertThat(bookTest04.isPresent()).isTrue();
        Book b2 = bookTest04.get();
        assertThat(b2.getId()).isNotNull();
        assertThat(b2.getTitle()).isEqualTo("teste title");
        assertThat(b2.getResume()).isEqualTo("teste resume");
        assertThat(b2.getIsbn()).isEqualTo("teste isbn");
        assertThat(b2.getAuthor()).isEqualTo("teste author");
    }

    @Test
    public void updateBook(){
        Optional<Book> book = this.bookRepository.findById(3L); //pega livro por id 3
        assertThat(book.isPresent()).isTrue();
        Book b3 = book.get();
        b3.setAuthor("Waldir");
        b3 = this.bookRepository.save(b3);
        assertThat(b3.getId()).isNotNull();
        assertThat(b3.getAuthor()).isEqualTo("Waldir");

    }

    @Test
    public void deleteBook(){
        Book bookTest05 = new Book("teste title 5","teste resume 5","teste isbn 5","teste author 5",DATA);
        bookTest05 = this.bookRepository.save(bookTest05);
        this.bookRepository.deleteById(bookTest05.getId());
        Optional<Book> b = this.bookRepository.findById(bookTest05.getId());
        assertThat(b.isPresent()).isFalse();
    }

    /*@Test
    public void FindPageBook(){
        Pageable paging = (Pageable) PageRequest.of(1,10,Sort.by(Sort.Direction.fromString("ASC"),"id"));
        Page<Book> pages = (Page<Book>) this.bookRepository.findAll();
        assertThat(pages.getTotalElements()).isEqualTo(1);
    }
    */
}
