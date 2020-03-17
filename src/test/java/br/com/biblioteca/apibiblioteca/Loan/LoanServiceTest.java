package br.com.biblioteca.apibiblioteca.Loan;

import br.com.biblioteca.apibiblioteca.domain.Book;
import br.com.biblioteca.apibiblioteca.domain.Loan;
import br.com.biblioteca.apibiblioteca.domain.User_app;
import br.com.biblioteca.apibiblioteca.repository.BookRepository;
import br.com.biblioteca.apibiblioteca.repository.LoanRepository;
import br.com.biblioteca.apibiblioteca.repository.User_appRepository;
import br.com.biblioteca.apibiblioteca.service.BookService;
import br.com.biblioteca.apibiblioteca.service.LoanService;
import br.com.biblioteca.apibiblioteca.service.User_appService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext
public class LoanServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private User_appService userAppService;

    @Autowired
    private LoanService loanService;

    private static Date DATA;
    private static User_app user01;
    private static User_app user02;
    private static List<Book> books01 = new ArrayList<>();
    private static List<Book> books02 = new ArrayList<>();
    @BeforeAll
    public void setUp() throws ParseException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        DATA = timestamp;

        //primeiro emprestimo
        User_app userTest01 = new User_app("teste nome 1",21,"46356357");
        userTest01 = this.userAppService.insert(userTest01);
        user01 = userTest01;

        Book bookTest01 = new Book("teste title 1","teste resume","teste isbn","teste author",DATA);
        List<Book> book01 = new ArrayList<>();
        book01.add(bookTest01);
        this.bookService.insert(bookTest01);
        books01 = book01;

        Loan loanTest01 = new Loan(userTest01,book01,"150 dias"); //id=2
        loanService.insert(loanTest01);

        //segundo emprestimo
        User_app userTest02 = new User_app("teste nome 2",21,"46356357");
        userTest02 = this.userAppService.insert(userTest02);

        Book bookTest02 = new Book("teste title 2","teste resume","teste isbn","teste author",DATA);
        List<Book> book02 = new ArrayList<>();
        book02.add(bookTest02);
        this.bookService.insert(bookTest02);

        Loan loanTest02 = new Loan(userTest02,book02,"200 dias"); //id=2
        loanService.insert(loanTest02);
    }

    @Test
    public void createLoan(){

        User_app userTest03 = new User_app("teste nome",21,"46356357");
        userTest03 = this.userAppService.insert(userTest03);

        Book bookTest03 = new Book("teste title","teste resume","teste isbn","teste author",DATA);
        List<Book> books03 = new ArrayList<>();
        books03.add(bookTest03);
        this.bookService.insert(bookTest03);

        Loan loanTest03 = new Loan(userTest03,books03,"250 dias"); //id=3
        loanTest03 = this.loanService.insert(loanTest03);

        assertThat(loanTest03.getId()).isNotNull();
    }

    @Test
    public void getIdLoan(){
        Loan loanTest04 = this.loanService.find(2L);
        assertThat(loanTest04.getId()).isNotNull();
        assertThat(loanTest04.getUser_app().getName()).isEqualTo(user01.getName());
        assertThat(loanTest04.getBooks().get(0).getAuthor()).isEqualTo(books01.get(0).getAuthor());
        assertThat(loanTest04.getLoan_time()).isEqualTo("150 dias");
    }

    @Test
    public void updateLoan(){
        Loan loanTest05 = this.loanService.find(3L);
        loanTest05.setLoan_time("15 dias");
        loanTest05 = this.loanService.update(loanTest05);
        assertThat(loanTest05.getId()).isNotNull();
        assertThat(loanTest05.getLoan_time()).isEqualTo("15 dias");

    }

    @Test
    public void deleteBook(){
        User_app userTest06 = new User_app("teste nome 2",22,"4635635754354");
        user02 = userTest06;
        user02 = this.userAppService.insert(user02);

        Book bookTest06 = new Book("teste title 2","teste resume 2","teste isbn 2","teste author 2",DATA);
        books02.add(bookTest06);
        this.bookService.insert(bookTest06);

        Loan loanTest06 = new Loan(user02,books02,"10 dias");
        loanTest06 = this.loanService.insert(loanTest06);
        this.loanService.delete(loanTest06.getId());
        Loan loan06 = this.loanService.find(loanTest06.getId());
        assertThat(loan06).isNull();
    }

}
