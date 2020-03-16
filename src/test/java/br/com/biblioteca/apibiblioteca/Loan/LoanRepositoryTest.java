package br.com.biblioteca.apibiblioteca.Loan;

import br.com.biblioteca.apibiblioteca.domain.Book;
import br.com.biblioteca.apibiblioteca.domain.Loan;
import br.com.biblioteca.apibiblioteca.domain.User_app;
import br.com.biblioteca.apibiblioteca.repository.BookRepository;
import br.com.biblioteca.apibiblioteca.repository.LoanRepository;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext
public class LoanRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private User_appRepository userRepository;

    @Autowired
    private LoanRepository loanRepository;

    private static Date DATA;
    private static List<Book> books = new ArrayList<>();
    private static User_app user;

    @BeforeAll
    public void setUp() throws ParseException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        DATA = timestamp;

        User_app userTest = new User_app("teste nome",21,"46356357");
        user = userTest;
        this.userRepository.save(user);

        Book bookTest = new Book("teste title","teste resume","teste isbn","teste author",DATA);
        books.add(bookTest);
        this.bookRepository.save(bookTest);

    }

    @Test
    public void createLoan(){
        Loan loanTest01 = new Loan(user,books,"150 dias");
        loanTest01 = loanRepository.save(loanTest01);
        assertThat(loanTest01.getId()).isNotNull();
    }

    @Test
    public void getIdLoan(){
        Optional<Loan> loanTest02 = this.loanRepository.findById(2L);
        assertThat(loanTest02.isPresent()).isTrue();
        Loan loan02 = loanTest02.get();
        assertThat(loan02.getId()).isNotNull();
        assertThat(loan02.getUser_app()).isEqualTo(user);
        assertThat(loan02.getBooks()).isEqualTo(books);
        assertThat(loan02.getLoan_time()).isEqualTo("50 dias");
    }

    @Test
    public void updateLoan(){
        Optional<Loan> loanTest04 = this.loanRepository.findById(3L);
        assertThat(loanTest04.isPresent()).isTrue();
        Loan loan03 = loanTest04.get();
        loan03.setLoan_time("15 dias");
        this.loanRepository.save(loan03);
        assertThat(loan03.getId()).isNotNull();
        assertThat(loan03.getLoan_time()).isEqualTo("15 dias");

    }

    @Test
    public void deleteBook(){
        User_app userTest04 = new User_app("teste nome 2",22,"4635635754354");

        Book bookTest04 = new Book("teste title 2","teste resume 2","teste isbn 2","teste author 2",DATA);
        books.add(bookTest04);

        Loan loanTest05 = new Loan(userTest04,books,"10 dias");
        userTest04.getLoans().add(loanTest05);
        this.loanRepository.deleteById(1L);
        Optional<Loan> loan04 = this.loanRepository.findById(1L);
        assertThat(loan04.isPresent()).isFalse();
    }

}
