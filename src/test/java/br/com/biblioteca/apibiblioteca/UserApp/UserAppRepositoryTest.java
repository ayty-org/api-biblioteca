package br.com.biblioteca.apibiblioteca.UserApp;

import br.com.biblioteca.apibiblioteca.user.UserApp;
import br.com.biblioteca.apibiblioteca.user.UserAppRepository;
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
/*
@ActiveProfiles("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext
public class UserAppRepositoryTest {

    @Autowired
    private UserAppRepository userRepository;

    @BeforeAll
    public void setUp() throws ParseException {

        UserApp userTest01 = new UserApp("teste nome 1",21,"46356357"); //id=2
        this.userRepository.save(userTest01);

        UserApp userTest02 = new UserApp("teste nome 2",31,"876353453"); //id=3
        this.userRepository.save(userTest02);
    }

    @Test
    public void createUser(){
        UserApp userTest03 = new UserApp("teste nome 3",43,"463563456357"); //id=4
        this.userRepository.save(userTest03);
        assertThat(userTest03.getId()).isNotNull();
    }

    @Test
    public void getIdUser(){
        Optional<UserApp> userTest04 = this.userRepository.findById(2L);
        assertThat(userTest04.isPresent()).isTrue();
        UserApp user = userTest04.get();
        assertThat(user.getId()).isNotNull();
        assertThat(user.getName()).isEqualTo("teste nome 1");
        assertThat(user.getAge()).isEqualTo(21);
        assertThat(user.getFone()).isEqualTo("46356357");
    }

    @Test
    public void updateUser(){
        Optional<UserApp> userTest05 = this.userRepository.findById(3L);
        assertThat(userTest05.isPresent()).isTrue();
        UserApp user = userTest05.get();
        user.setName("Waldir");
        this.userRepository.save(user);
        assertThat(user.getId()).isNotNull();
        assertThat(user.getName()).isEqualTo("Waldir");

    }

    @Test
    public void deleteUser(){
        UserApp userTest06 = new UserApp("teste nome 6",23,"463563456357");
        userTest06 = this.userRepository.save(userTest06);
        this.userRepository.deleteById(userTest06.getId());
        Optional<UserApp> user = this.userRepository.findById(userTest06.getId());
        assertThat(user.isPresent()).isFalse();
    }

    @Test
    public void FindPageBook(){
        Pageable paging = (Pageable) PageRequest.of(1,10,Sort.by(Sort.Direction.fromString("ASC"),"id"));
        Page<Book> pages = (Page<Book>) this.bookRepository.findAll();
        assertThat(pages.getTotalElements()).isEqualTo(1);
    }

}
*/