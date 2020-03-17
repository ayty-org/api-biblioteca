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

        User_app userTest01 = new User_app("teste nome 1",21,"46356357"); //id=2
        this.userService.insert(userTest01);

        User_app userTest02 = new User_app("teste nome 2",31,"876353453"); //id=3
        this.userService.insert(userTest02);
    }

    @Test
    public void createUser(){
        User_app userTest03 = new User_app("teste nome 3",43,"463563456357"); //id=4
        userTest03 = this.userService.insert(userTest03);
        assertThat(userTest03.getId()).isNotNull();
    }

    @Test
    public void getIdUser(){
        User_app userTest04 = this.userService.find(2L);
        assertThat(userTest04.getId()).isNotNull();
        assertThat(userTest04.getName()).isEqualTo("teste nome 1");
        assertThat(userTest04.getAge()).isEqualTo(21);
        assertThat(userTest04.getFone()).isEqualTo("46356357");
    }

    @Test
    public void updateUser(){
        User_app userTest05 = this.userService.find(3L);
        userTest05.setName("Waldir");
        userTest05 = this.userService.update(userTest05);
        assertThat(userTest05.getId()).isNotNull();
        assertThat(userTest05.getName()).isEqualTo("Waldir");

    }

    @Test
    public void deleteUser(){
        User_app userTest06 = new User_app("teste nome 2",423,"463563456357");
        userTest06 = this.userService.insert(userTest06);
        this.userService.delete(userTest06.getId());
        userTest06 = this.userService.find(userTest06.getId());
        assertThat(userTest06).isNull();
    }

    /*@Test
    public void FindPageBook(){
        Pageable paging = (Pageable) PageRequest.of(1,10,Sort.by(Sort.Direction.fromString("ASC"),"id"));
        Page<Book> pages = (Page<Book>) this.bookRepository.findAll();
        assertThat(pages.getTotalElements()).isEqualTo(1);
    }
    */

}
