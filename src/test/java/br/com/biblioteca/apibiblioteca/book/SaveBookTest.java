package br.com.biblioteca.apibiblioteca.book;

import br.com.biblioteca.apibiblioteca.book.services.SaveBookImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.biblioteca.apibiblioteca.book.builders.BookBuilder.createBook;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por salvar um book")
public class SaveBookTest {

    @Mock
    private BookRepository bookRepository;
    private SaveBookImpl saveBook;

    @BeforeEach
    public void setUp() {
        this.saveBook = new SaveBookImpl(bookRepository);
    }

    @Test
    @DisplayName("Deve salvar um livro")
    void shouldSaveBook() { //testando save book

        //execução
        saveBook.insert(createBook().build());

        //preparação
        ArgumentCaptor<Book> captorBook = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(captorBook.capture());

        Book result = captorBook.getValue();

        //verificação
        assertAll("book",
                () -> assertThat(result.getAuthor(), is("teste author")),
                () -> assertThat(result.getResume(), is("teste resume")),
                () -> assertThat(result.getIsbn(), is("teste isbn")),
                () -> assertThat(result.getTitle(), is("teste title"))
        );
    }
}
