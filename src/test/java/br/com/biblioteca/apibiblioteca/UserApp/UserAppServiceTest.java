package br.com.biblioteca.apibiblioteca.UserApp;

import br.com.biblioteca.apibiblioteca.domain.User_app;
import br.com.biblioteca.apibiblioteca.repository.User_appRepository;
import br.com.biblioteca.apibiblioteca.service.User_appService;
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
public class UserAppServiceTest {

    @Autowired
    private User_appService userService;

    @BeforeAll
    public void setUp() throws ParseException {

        User_app userTest = new User_app("teste nome",21,"46356357");
        this.userService.insert(userTest);
    }

    @Test
    public void createUser(){
        User_app userTest01 = new User_app("teste nome 2",423,"463563456357");
        this.userService.insert(userTest01);
        assertThat(userTest01.getId()).isNotNull();
    }

    @Test
    public void getIdUser(){
        User_app userTest02 = this.userService.find(2L);
        assertThat(userTest02.getId()).isNotNull();
        assertThat(userTest02.getName()).isEqualTo("teste nome");
        assertThat(userTest02.getAge()).isEqualTo(21);
        assertThat(userTest02.getFone()).isEqualTo("46356357");
    }

    @Test
    public void updateUser(){
        User_app userTest03 = new User_app("teste nome 2",423,"463563456357");
        this.userService.insert(userTest03);
        User_app userTest04 = this.userService.find(3L);
        userTest04.setName("Waldir");
        this.userService.insert(userTest04);
        assertThat(userTest04.getId()).isNotNull();
        assertThat(userTest04.getName()).isEqualTo("Waldir");

    }

    @Test
    public void deleteUser(){
        User_app userTest03 = new User_app("teste nome 2",423,"463563456357");
        this.userService.insert(userTest03);
        this.userService.delete(userTest03.getId());
        User_app user04 = this.userService.find(userTest03.getId());
        assertThat(user04.getId()).isNull();
    }

    /*@Test
    public void FindPageBook(){
        Pageable paging = (Pageable) PageRequest.of(1,10,Sort.by(Sort.Direction.fromString("ASC"),"id"));
        Page<Book> pages = (Page<Book>) this.bookRepository.findAll();
        assertThat(pages.getTotalElements()).isEqualTo(1);
    }
    */

}
