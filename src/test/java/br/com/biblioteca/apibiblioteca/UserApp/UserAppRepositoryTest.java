package br.com.biblioteca.apibiblioteca.UserApp;

import br.com.biblioteca.apibiblioteca.domain.User_app;
import br.com.biblioteca.apibiblioteca.repository.User_appRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext
public class UserAppRepository {

    @Autowired
    private User_appRepository userRepository;

    @BeforeAll
    public void setUp() throws ParseException {

        User_app userTest = new User_app("teste nome",21,"46356357");
        userTest = this.userRepository.save(userTest);
    }

    @Test
    public void createBook(){
        User_app userTest01 = new User_app("teste nome 2",423,"463563456357");
        userTest01 = this.userRepository.save(userTest01);
        assertThat(userTest01.getId()).isNotNull();
    }

    @Test
    public void getIdBook(){
        Optional<User_app> userTest02 = this.userRepository.findById(2L);
        assertThat(userTest02.isPresent()).isTrue();
        User_app user02 = userTest02.get();
        assertThat(user02.getId()).isNotNull();
        assertThat(user02.getName()).isEqualTo("teste nome");
        assertThat(user02.getAge()).isEqualTo(21);
        assertThat(user02.getFone()).isEqualTo("46356357");
    }

    @Test
    public void updateBook(){
        User_app userTest03 = new User_app("teste nome 2",423,"463563456357");
        this.userRepository.save(userTest03);
        Optional<User_app> userTest04 = this.userRepository.findById(3L);
        assertThat(userTest04.isPresent()).isTrue();
        User_app user03 = userTest04.get();
        user03.setName("Waldir");
        user03 = this.userRepository.save(user03);
        assertThat(user03.getId()).isNotNull();
        assertThat(user03.getName()).isEqualTo("Waldir");

    }

    @Test
    public void deleteBook(){
        User_app userTest03 = new User_app("teste nome 2",423,"463563456357");
        this.userRepository.save(userTest03);
        this.userRepository.deleteById(userTest03.getId());
        Optional<User_app> user04 = this.userRepository.findById(userTest03.getId());
        assertThat(user04.isPresent()).isFalse();
    }

    /*@Test
    public void FindPageBook(){
        Pageable paging = (Pageable) PageRequest.of(1,10,Sort.by(Sort.Direction.fromString("ASC"),"id"));
        Page<Book> pages = (Page<Book>) this.bookRepository.findAll();
        assertThat(pages.getTotalElements()).isEqualTo(1);
    }
    */
}
