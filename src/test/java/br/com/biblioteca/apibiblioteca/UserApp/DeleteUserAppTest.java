package br.com.biblioteca.apibiblioteca.UserApp;

import br.com.biblioteca.apibiblioteca.exceptions.UserAppNotDeletedException;
import br.com.biblioteca.apibiblioteca.user.UserApp;
import br.com.biblioteca.apibiblioteca.user.UserAppRepository;
import br.com.biblioteca.apibiblioteca.user.services.DeleteUserAppImpl;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por deletar um UserApp")
public class DeleteUserAppTest {

    @Mock
    private UserAppRepository userAppRepository;
    private DeleteUserAppImpl deleteUserApp;

    @BeforeEach
    public void setUp() {
        this.deleteUserApp = new DeleteUserAppImpl(userAppRepository);
    }

    @Test
    @DisplayName("Deve deletar um livro")
    void shouldUserAppDeleted() {
        userAppRepository.save(createUserApp().build());

        ArgumentCaptor<UserApp> captorBook = ArgumentCaptor.forClass(UserApp.class);
        verify(userAppRepository).save(captorBook.capture());

        UserApp result = captorBook.getValue();

        assertAll("UserApp",
                () -> assertThat(result.getName(), is("teste nome")),
                () -> assertThat(result.getAge(), is(20)),
                () -> assertThat(result.getFone(), is("teste fone"))

        );

        deleteUserApp.delete(result.getId());

        assertNull(result);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o usuário não puder ser excluido")
    void shouldThrowUserAppNotDeletedException() {
        when(userAppRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(UserAppNotDeletedException.class, () -> this.deleteUserApp.delete(1L));
    }
}
