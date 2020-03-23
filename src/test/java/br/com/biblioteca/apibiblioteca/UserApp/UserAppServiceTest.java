package br.com.biblioteca.apibiblioteca.UserApp;

import br.com.biblioteca.apibiblioteca.user.UserApp;
import br.com.biblioteca.apibiblioteca.user.services.UserAppService;
import br.com.biblioteca.apibiblioteca.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext
public class UserAppServiceTest {

    @Autowired
    private UserAppService userService;

    @BeforeAll
    public void setUp() throws ParseException {

        UserApp userTest01 = new UserApp("teste nome 1",21,"46356357"); //id=2
        this.userService.insert(userTest01);

        UserApp userTest02 = new UserApp("teste nome 2",31,"876353453"); //id=3
        this.userService.insert(userTest02);
    }

    @Test
    public void createUser(){
        UserApp userTest03 = new UserApp("teste nome 3",43,"463563456357"); //id=4
        this.userService.insert(userTest03);
        assertThat(userTest03.getId()).isNotNull();
    }

    @Test
    public void getIdUser(){
        UserApp userTest04 = this.userService.find(2L);
        assertThat(userTest04.getId()).isNotNull();
        assertThat(userTest04.getName()).isEqualTo("teste nome 1");
        assertThat(userTest04.getAge()).isEqualTo(21);
        assertThat(userTest04.getFone()).isEqualTo("46356357");
    }

    @Test
    public void updateUser(){
        UserApp userTest05 = this.userService.find(3L);
        userTest05.setName("Waldir");
        this.userService.update(userTest05);
        assertThat(userTest05.getId()).isNotNull();
        assertThat(userTest05.getName()).isEqualTo("Waldir");

    }

    @Test
    public void deleteUser(){
        UserApp userTest06 = new UserApp("teste nome 2",423,"463563456357");
        this.userService.insert(userTest06);
        this.userService.delete(userTest06.getId());
        try {
            userTest06 = this.userService.find(userTest06.getId());
        }catch (ObjectNotFoundException o){
            userTest06=null;
        }
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
