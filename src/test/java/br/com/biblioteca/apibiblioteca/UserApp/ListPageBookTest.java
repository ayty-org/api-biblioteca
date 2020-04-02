package br.com.biblioteca.apibiblioteca.UserApp;

import br.com.biblioteca.apibiblioteca.user.UserAppRepository;
import br.com.biblioteca.apibiblioteca.user.services.ListPageUserAppImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por pesquisar UserApp por paginação")
public class ListPageBookTest {

    @Mock
    private UserAppRepository userAppRepository;
    private ListPageUserAppImpl findPageUserApp;

    @BeforeEach
    public void setUp() {
        this.findPageUserApp = new ListPageUserAppImpl(userAppRepository);
    }

}
