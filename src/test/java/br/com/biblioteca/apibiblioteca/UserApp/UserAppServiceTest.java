package br.com.biblioteca.apibiblioteca.UserApp;

import br.com.biblioteca.apibiblioteca.exceptions.UserAppNotFoundException;
import br.com.biblioteca.apibiblioteca.user.UserApp;
import br.com.biblioteca.apibiblioteca.user.UserAppRepository;
import br.com.biblioteca.apibiblioteca.user.services.FindUserApp;
import br.com.biblioteca.apibiblioteca.user.services.FindUserAppImpl;
import br.com.biblioteca.apibiblioteca.user.services.InsertUserAppImpl;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.biblioteca.apibiblioteca.UserApp.builders.UserAppBuilder.createUserApp;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por gerenciar UserApp")
public class UserAppServiceTest {

    @Mock
    private UserAppRepository userAppRepository;

    private InsertUserAppImpl insertUserApp;

    private FindUserApp findUserApp;

    @BeforeEach
    public void setUp() {
        this.insertUserApp = new InsertUserAppImpl(userAppRepository);
        this.findUserApp = new FindUserAppImpl(userAppRepository);
    }

    @Test
    @DisplayName("Deve criar um usuário")
    public void shouldCreateBook(){
        //execução
        insertUserApp.insert(createUserApp().build());

        //preparação
        ArgumentCaptor<UserApp> captorUserApp = ArgumentCaptor.forClass(UserApp.class);
        verify(userAppRepository).save(captorUserApp.capture());

        UserApp result = captorUserApp.getValue();

        //verificação
        assertAll("book",
                () -> MatcherAssert.assertThat(result.getName(), is("teste nome")),
                () -> MatcherAssert.assertThat(result.getAge(), is(20)),
                () -> MatcherAssert.assertThat(result.getFone(), is("teste fone"))
        );
    }

    @Test
    @DisplayName("Deve retornar um usuário")
    void shouldFindById() {
        when(userAppRepository.findById(anyLong())).thenReturn(
                Optional.of(createUserApp().name("Nome Teste GET").build())
        );

        UserApp result = this.findUserApp.find(1L);

        //verificação
        assertAll("book",
                () -> MatcherAssert.assertThat(result.getName(), is("Nome Teste GET")),
                () -> MatcherAssert.assertThat(result.getAge(), is(20)),
                () -> MatcherAssert.assertThat(result.getFone(), is("teste fone"))
        );
    }

    @Test
    @DisplayName("Deve lançar exceção quando o usuário não for encontrado")
    void shouldThrowUserAppNotFoundException() {
        when(userAppRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(UserAppNotFoundException.class, () -> this.findUserApp.find(1L));
    }
}
