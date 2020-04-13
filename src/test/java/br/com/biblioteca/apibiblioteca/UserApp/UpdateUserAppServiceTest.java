package br.com.biblioteca.apibiblioteca.UserApp;

import br.com.biblioteca.apibiblioteca.user.UserApp;
import br.com.biblioteca.apibiblioteca.user.UserAppRepository;
import br.com.biblioteca.apibiblioteca.user.services.UpdateUserAppServiceImpl;
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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por atualizar UserApp")
public class UpdateUserAppServiceTest {

    @Mock
    private UserAppRepository userAppRepository;
    private UpdateUserAppServiceImpl updateUserApp;

    @BeforeEach
    public void setUp() {
        this.updateUserApp = new UpdateUserAppServiceImpl(userAppRepository);
    }

    @Test
    @DisplayName("Deve atualizar um usuário")
    void shouldUpdateUserApp() {

        when(userAppRepository.findById(1L)).thenReturn(Optional.of(createUserApp().id(1L).build()));

        updateUserApp.update(createUserApp().name("teste update").build(), 1L);

        ArgumentCaptor<UserApp> captorUser = ArgumentCaptor.forClass(UserApp.class);
        verify(userAppRepository).save(captorUser.capture());

        UserApp result = captorUser.getValue();

        assertAll("UserApp",
                () -> assertThat(result.getName(), is("teste update")),
                () -> assertThat(result.getAge(), is(20)),
                () -> assertThat(result.getFone(), is("teste fone"))
        );
    }
}
