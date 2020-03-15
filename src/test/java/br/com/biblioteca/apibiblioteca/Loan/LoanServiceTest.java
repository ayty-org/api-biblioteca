package br.com.biblioteca.apibiblioteca.Loan;

import br.com.biblioteca.apibiblioteca.domain.Book;
import br.com.biblioteca.apibiblioteca.domain.Loan;
import br.com.biblioteca.apibiblioteca.domain.User_app;
import br.com.biblioteca.apibiblioteca.repository.BookRepository;
import br.com.biblioteca.apibiblioteca.repository.LoanRepository;
import br.com.biblioteca.apibiblioteca.repository.User_appRepository;
import br.com.biblioteca.apibiblioteca.service.LoanService;
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
    private LoanService loanService;

    private static Date DATA;
    private static List<Book> books = new ArrayList<>();;
    private static User_app user;

    @BeforeAll
    public void setUp() throws ParseException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        DATA = timestamp;

        User_app userTest = new User_app("teste nome",21,"46356357");
        user = userTest;
        //this.userRepository.save(user);

        Book bookTest = new Book("teste title","teste resume","teste isbn","teste author",DATA);
        books.add(bookTest);
        //this.bookRepository.save(bookTest);

        Loan loanTest = new Loan(user,books,"50 dias");
        //this.loanRepository.save(loanTest);
        user.getLoans().add(loanTest);
    }

    @Test
    public void createLoan(){
        User_app userTest01 = new User_app("teste nome 2",22,"4635635754354");

        Book bookTest01 = new Book("teste title 2","teste resume 2","teste isbn 2","teste author 2",DATA);
        books.add(bookTest01);

        Loan loanTest01 = new Loan(userTest01,books,"150 dias");
        userTest01.getLoans().add(loanTest01);
        assertThat(loanTest01.getId()).isNotNull();
    }

    @Test
    public void getIdLoan(){
        Loan loanTest02 = this.loanService.find(2L);
        assertThat(loanTest02.getId()).isNotNull();
        assertThat(loanTest02.getUser_app()).isEqualTo(user);
        assertThat(loanTest02.getBooks()).isEqualTo(books);
        assertThat(loanTest02.getLoan_time()).isEqualTo("50 dias");
    }

    @Test
    public void updateLoan(){
        User_app userTest03 = new User_app("teste nome 2",22,"4635635754354");

        Book bookTest03 = new Book("teste title 2","teste resume 2","teste isbn 2","teste author 2",DATA);
        books.add(bookTest03);

        Loan loanTest03 = new Loan(userTest03,books,"10 dias");
        userTest03.getLoans().add(loanTest03);

        Loan loanTest04 = this.loanService.find(3L);
        loanTest04.setLoan_time("15 dias");
        this.loanService.insert(loanTest04);
        assertThat(loanTest04.getId()).isNotNull();
        assertThat(loanTest04.getLoan_time()).isEqualTo("15 dias");

    }

    @Test
    public void deleteBook(){
        User_app userTest04 = new User_app("teste nome 2",22,"4635635754354");

        Book bookTest04 = new Book("teste title 2","teste resume 2","teste isbn 2","teste author 2",DATA);
        books.add(bookTest04);

        Loan loanTest05 = new Loan(userTest04,books,"10 dias");
        userTest04.getLoans().add(loanTest05);
        this.loanService.delete(1L);
        Loan loan04 = this.loanService.find(1L);
        assertThat(loan04.getId()).isNull();
    }

}
