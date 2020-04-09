package br.com.biblioteca.apibiblioteca.book;

import br.com.biblioteca.apibiblioteca.book.services.DeleteBookServiceImpl;
import br.com.biblioteca.apibiblioteca.exceptions.BookNotDeletedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por deletar um book")
public class DeleteBookServiceTest {

    @Mock
    private BookRepository bookRepository;
    private DeleteBookServiceImpl deleteBook;

    @BeforeEach
    public void setUp() {
        this.deleteBook = new DeleteBookServiceImpl(bookRepository);
    }

    @Test
    @DisplayName("Deve deletar um livro")
    void shouldBookDeleted() {
        when(bookRepository.existsById(1L)).thenReturn(true);
        deleteBook.delete(1L);
        verify(bookRepository).existsById(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o livro não puder ser excluido")
    void shouldThrowBookNotDeletedException() {
        lenient().when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(BookNotDeletedException.class, () -> this.deleteBook.delete(1L));
    }
}
