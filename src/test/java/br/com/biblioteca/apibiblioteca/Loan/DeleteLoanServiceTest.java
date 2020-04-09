package br.com.biblioteca.apibiblioteca.Loan;

import br.com.biblioteca.apibiblioteca.exceptions.LoanNotDeletedException;
import br.com.biblioteca.apibiblioteca.loan.LoanRepository;
import br.com.biblioteca.apibiblioteca.loan.services.DeleteLoanServiceImpl;
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
@DisplayName("Valida funcionalidade do serviço responsável por deletar um Loan")
public class DeleteLoanServiceTest {

    @Mock
    private LoanRepository loanRepository;
    private DeleteLoanServiceImpl deleteLoan;

    @BeforeEach
    public void setUp() {
        this.deleteLoan = new DeleteLoanServiceImpl(loanRepository);
    }

    @Test
    @DisplayName("Deve deletar um emprestimo")
    void shouldLoanDeleted() {
        when(loanRepository.existsById(1L)).thenReturn(true);
        deleteLoan.delete(1L);
        verify(loanRepository).existsById(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o emprestimo não puder ser excluido")
    void shouldThrowLoanNotDeletedException() {
        lenient().when(loanRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(LoanNotDeletedException.class, () -> this.deleteLoan.delete(1L));
    }
}
