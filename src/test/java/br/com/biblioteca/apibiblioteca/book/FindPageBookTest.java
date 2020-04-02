package br.com.biblioteca.apibiblioteca.book;

import br.com.biblioteca.apibiblioteca.book.services.FindPageBookImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por pesquisar books por paginação")
public class FindPageBookTest {

    @Mock
    private BookRepository bookRepository;
    private FindPageBookImpl findPageBook;

    @BeforeEach
    public void setUp() {
        this.findPageBook = new FindPageBookImpl(bookRepository);
    }

}
