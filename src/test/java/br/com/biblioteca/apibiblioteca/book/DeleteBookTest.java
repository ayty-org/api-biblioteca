package br.com.biblioteca.apibiblioteca.book;

import br.com.biblioteca.apibiblioteca.book.services.DeleteBookImpl;
import br.com.biblioteca.apibiblioteca.exceptions.BookNotDeletedException;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por deletar um book")
public class DeleteBookTest {

    @Mock
    private BookRepository bookRepository;
    private DeleteBookImpl deleteBook;

    @BeforeEach
    public void setUp() {
        this.deleteBook = new DeleteBookImpl(bookRepository);
    }

    @Test
    @DisplayName("Deve deletar um livro")
    void shouldBookDeleted() {
        bookRepository.save(createBook().build());

        ArgumentCaptor<Book> captorBook = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(captorBook.capture());

        Book result = captorBook.getValue();

        assertAll("book",
                () -> assertThat(result.getAuthor(), is("teste author")),
                () -> assertThat(result.getResume(), is("teste resume")),
                () -> assertThat(result.getIsbn(), is("teste isbn")),
                () -> assertThat(result.getTitle(), is("teste title"))
        );

        deleteBook.delete(result.getId());

        assertNull(result);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o livro não puder ser excluido")
    void shouldThrowBookNotDeletedException() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(BookNotDeletedException.class, () -> this.deleteBook.delete(1L));
    }
}
