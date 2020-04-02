package br.com.biblioteca.apibiblioteca.UserApp;

import br.com.biblioteca.apibiblioteca.user.UserApp;
import br.com.biblioteca.apibiblioteca.user.UserAppRepository;
import br.com.biblioteca.apibiblioteca.user.services.SaveUserAppImpl;
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
@DisplayName("Valida funcionalidade do serviço responsável por salvar um UserApp")
public class SaveUserAppTest {

    @Mock
    private UserAppRepository userAppRepository;
    private SaveUserAppImpl saveUserApp;

    @BeforeEach
    public void setUp() {
        this.saveUserApp = new SaveUserAppImpl(userAppRepository);
    }

    @Test
    @DisplayName("Deve salvar um usuário")
    void shouldSaveUserApp() {

        //execução
        saveUserApp.insert(createUserApp().build());

        //preparação
        ArgumentCaptor<UserApp> captorUserApp = ArgumentCaptor.forClass(UserApp.class);
        verify(userAppRepository).save(captorUserApp.capture());

        UserApp result = captorUserApp.getValue();

        assertAll("UserApp",
                () -> assertThat(result.getName(), is("teste nome")),
                () -> assertThat(result.getAge(), is(20)),
                () -> assertThat(result.getFone(), is("teste fone"))

        );
    }
}
