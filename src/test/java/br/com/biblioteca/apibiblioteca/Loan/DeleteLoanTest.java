package br.com.biblioteca.apibiblioteca.Loan;

import br.com.biblioteca.apibiblioteca.exceptions.BookNotDeletedException;
import br.com.biblioteca.apibiblioteca.loan.Loan;
import br.com.biblioteca.apibiblioteca.loan.LoanRepository;
import br.com.biblioteca.apibiblioteca.loan.services.DeleteLoanImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.biblioteca.apibiblioteca.Loan.builders.LoanBuilder.createLoan;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por deletar um Loan")
public class DeleteLoanTest {

    @Mock
    private LoanRepository loanRepository;
    private DeleteLoanImpl deleteLoan;

    @BeforeEach
    public void setUp() {
        this.deleteLoan = new DeleteLoanImpl(loanRepository);
    }

    @Test
    @DisplayName("Deve deletar um emprestimo")
    void shouldUserAppDeleted() {
        loanRepository.save(createLoan().build());

        ArgumentCaptor<Loan> captorLoan = ArgumentCaptor.forClass(Loan.class);
        verify(loanRepository).save(captorLoan.capture());

        Loan result = captorLoan.getValue();

        assertAll("UserApp",
                () -> assertThat(result.getUserApp().getName(), is("teste nome")),
                () -> assertThat(result.getBooks().get(0).getTitle(), is("teste title")),
                () -> assertThat(result.getLoanTime(), is("50 dias"))

        );

        deleteLoan.delete(result.getId());

        assertNull(result);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o emprestimo não puder ser excluido")
    void shouldThrowLoanNotDeletedException() {
        when(loanRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(BookNotDeletedException.class, () -> this.deleteLoan.delete(1L));
    }
}
