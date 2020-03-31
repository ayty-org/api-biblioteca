package br.com.biblioteca.apibiblioteca.book;

import br.com.biblioteca.apibiblioteca.book.services.FindBookImpl;
import br.com.biblioteca.apibiblioteca.book.services.UpdateBookImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.biblioteca.apibiblioteca.book.builders.BookBuilder.createBook;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por atualizar book")
public class UpdateBookTest {

    @Mock
    private BookRepository bookRepository;
    private UpdateBookImpl updateBook;
    private FindBookImpl findBook;

    @BeforeEach
    public void setUp() {
        this.updateBook = new UpdateBookImpl(bookRepository, findBook);

    }

    @Test
    @DisplayName("Deve retornar todos os livros")
    void shouldFindAllBook() { // testando buscar livro por id

        this.updateBook.update(createBook().author("teste update").build());

        Book result = this.findBook.find(1L);

        //verificação
        assertAll("book",
                () -> assertThat(result.getAuthor(), is("teste update")),
                () -> assertThat(result.getResume(), is("teste resume")),
                () -> assertThat(result.getIsbn(), is("teste isbn")),
                () -> assertThat(result.getTitle(), is("teste title"))
        );
    }
}

