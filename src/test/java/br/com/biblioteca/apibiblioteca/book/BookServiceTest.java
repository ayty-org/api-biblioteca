package br.com.biblioteca.apibiblioteca.book;

import br.com.biblioteca.apibiblioteca.book.services.FindBookImpl;
import br.com.biblioteca.apibiblioteca.book.services.InsertBookImpl;
import br.com.biblioteca.apibiblioteca.exceptions.BookNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.biblioteca.apibiblioteca.book.builders.BookBuilder.createBook;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por gerenciar books")
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    private InsertBookImpl insertBook;

    private FindBookImpl findBook;

    @BeforeEach
    public void setUp() {
        this.insertBook = new InsertBookImpl(bookRepository);
        this.findBook = new FindBookImpl(bookRepository);
    }

    @Test
    @DisplayName("Deve criar um livro")
    void shouldCreateBook() {

        //execução
        insertBook.insert(createBook().build());

        //preparação
        ArgumentCaptor<Book> captorBook = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(captorBook.capture());

        Book result = captorBook.getValue();

        //verificação
        assertAll("book",
                () -> assertThat(result.getAuthor(), is("teste author")),
                () -> assertThat(result.getResume(), is("teste resume")),
                () -> assertThat(result.getIsbn(), is("teste isbn")),
                () -> assertThat(result.getTitle(),is("teste title"))
        );
    }

    @Test
    @DisplayName("Deve retornar um livro")
    void shouldFindById() {
        when(bookRepository.findById(anyLong())).thenReturn(
                Optional.of(createBook().author("Author Teste GET").build())
        );

        Book result = this.findBook.find(1L);

        //verificação
        assertAll("book",
                () -> assertThat(result.getAuthor(), is("Author Teste GET")),
                () -> assertThat(result.getResume(), is("teste resume")),
                () -> assertThat(result.getIsbn(), is("teste isbn")),
                () -> assertThat(result.getTitle(), is("teste title"))
        );
    }

    @Test
    @DisplayName("Deve lançar exceção quando o livro não for encontrado")
    void shouldThrowBookNotFoundException() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(BookNotFoundException.class, () -> this.findBook.find(1L));
    }
}
