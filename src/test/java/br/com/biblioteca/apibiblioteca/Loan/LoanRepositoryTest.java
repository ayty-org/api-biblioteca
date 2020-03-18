package br.com.biblioteca.apibiblioteca.Loan;

import br.com.biblioteca.apibiblioteca.domain.Book;
import br.com.biblioteca.apibiblioteca.domain.Loan;
import br.com.biblioteca.apibiblioteca.domain.UserApp;
import br.com.biblioteca.apibiblioteca.repository.BookRepository;
import br.com.biblioteca.apibiblioteca.repository.LoanRepository;
import br.com.biblioteca.apibiblioteca.repository.UserAppRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Timestamp;
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
    private UserAppRepository userRepository;

    @Autowired
    private LoanRepository loanRepository;

    private static Date DATA;
    private static UserApp user01;
    private static UserApp user02;
    private static List<Book> books01 = new ArrayList<>();
    private static List<Book> books02 = new ArrayList<>();

    @BeforeAll
    public void setUp(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        DATA = timestamp;

        //primeiro emprestimo
        UserApp userTest01 = new UserApp("teste nome 1",21,"46356357");
        userTest01 = this.userRepository.save(userTest01);
        user01 = userTest01;

        Book bookTest01 = new Book("teste title 1","teste resume","teste isbn","teste author",DATA);
        List<Book> book01 = new ArrayList<>();
        book01.add(bookTest01);
        this.bookRepository.save(bookTest01);
        books01 = book01;

        Loan loanTest01 = new Loan(userTest01,book01,"150 dias"); //id=2
        loanRepository.save(loanTest01);

        //segundo emprestimo
        UserApp userTest02 = new UserApp("teste nome 2",21,"46356357");
        userTest02 = this.userRepository.save(userTest02);

        Book bookTest02 = new Book("teste title 2","teste resume","teste isbn","teste author",DATA);
        List<Book> book02 = new ArrayList<>();
        book02.add(bookTest02);
        this.bookRepository.save(bookTest02);

        Loan loanTest02 = new Loan(userTest02,book02,"200 dias"); //id=2
        loanRepository.save(loanTest02);

    }

    @Test
    public void createLoan(){
        UserApp userTest03 = new UserApp("teste nome",21,"46356357");
        userTest03 = this.userRepository.save(userTest03);

        Book bookTest03 = new Book("teste title","teste resume","teste isbn","teste author",DATA);
        List<Book> books03 = new ArrayList<>();
        books03.add(bookTest03);
        this.bookRepository.save(bookTest03);

        Loan loanTest03 = new Loan(userTest03,books03,"250 dias"); //id=3
        loanTest03 = this.loanRepository.save(loanTest03);

        assertThat(loanTest03.getId()).isNotNull();
    }

    @Test
    public void getIdLoan(){
        Optional<Loan> loanTest04 = this.loanRepository.findById(2L);
        assertThat(loanTest04.isPresent()).isTrue();
        Loan loan04 = loanTest04.get();
        assertThat(loan04.getId()).isNotNull();
        assertThat(loan04.getUserApp().getName()).isEqualTo(user01.getName());
        assertThat(loan04.getBooks().get(0).getAuthor()).isEqualTo(books01.get(0).getAuthor());
        assertThat(loan04.getLoanTime()).isEqualTo("150 dias");
    }

    @Test
    public void updateLoan(){
        Optional<Loan> loanTest05 = this.loanRepository.findById(3L);
        assertThat(loanTest05.isPresent()).isTrue();
        Loan loan05 = loanTest05.get();
        loan05.setLoanTime("15 dias");
        loan05 = this.loanRepository.save(loan05);
        assertThat(loan05.getId()).isNotNull();
        assertThat(loan05.getLoanTime()).isEqualTo("15 dias");
    }

    @Test
    public void deleteBook(){
        UserApp userTest06 = new UserApp("teste nome 3",21,"46356357");
        user02 = userTest06;
        user02 = this.userRepository.save(user02);

        Book bookTest06 = new Book("teste title 3","teste resume","teste isbn","teste author",DATA);
        books02.add(bookTest06);
        this.bookRepository.save(bookTest06);

        Loan loanTest06 = new Loan(user02,books02,"200 dias"); //id=2
        loanTest06 = loanRepository.save(loanTest06);

        this.loanRepository.deleteById(loanTest06.getId());
        Optional<Loan> loan06 = this.loanRepository.findById(loanTest06.getId());
        assertThat(loan06.isPresent()).isFalse();
    }

}
