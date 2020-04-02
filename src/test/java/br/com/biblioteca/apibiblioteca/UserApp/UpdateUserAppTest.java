package br.com.biblioteca.apibiblioteca.UserApp;

import br.com.biblioteca.apibiblioteca.user.UserApp;
import br.com.biblioteca.apibiblioteca.user.UserAppRepository;
import br.com.biblioteca.apibiblioteca.user.services.UpdateUserAppImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.biblioteca.apibiblioteca.UserApp.builders.UserAppBuilder.createUserApp;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por atualizar UserApp")
public class UpdateUserAppTest {

    @Mock
    private UserAppRepository userAppRepository;
    private UpdateUserAppImpl updateUserApp;

    @BeforeEach
    public void setUp() {
        this.updateUserApp = new UpdateUserAppImpl(userAppRepository);
    }

    @Test
    @DisplayName("Deve atualizar um usuário")
    void shouldUpdateUserApp() {

        //execução
        updateUserApp.update(createUserApp().name("teste update").build(), 1L);

        //preparação
        ArgumentCaptor<UserApp> captorUserApp = ArgumentCaptor.forClass(UserApp.class);
        verify(userAppRepository).save(captorUserApp.capture());

        UserApp result = captorUserApp.getValue();

        assertAll("UserApp",
                () -> assertThat(result.getName(), is("teste update")),
                () -> assertThat(result.getAge(), is(20)),
                () -> assertThat(result.getFone(), is("teste fone"))

        );
    }
}

