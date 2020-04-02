package br.com.biblioteca.apibiblioteca.Loan;

import br.com.biblioteca.apibiblioteca.exceptions.LoanNotFoundException;
import br.com.biblioteca.apibiblioteca.loan.Loan;
import br.com.biblioteca.apibiblioteca.loan.LoanRepository;
import br.com.biblioteca.apibiblioteca.loan.services.FindLoanImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.biblioteca.apibiblioteca.Loan.builders.LoanBuilder.createLoan;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por pesquisar um Loan")
public class FindLoanTest {

    @Mock
    private LoanRepository loanRepository;
    private FindLoanImpl findLoan;

    @BeforeEach
    public void setUp() {
        this.findLoan = new FindLoanImpl(loanRepository);
    }

    @Test
    @DisplayName("Deve retornar um emprestimo")
    void shouldFindByIdLoan() {

        when(loanRepository.findById(anyLong())).thenReturn(
                Optional.of(createLoan().loanTime("Loan Teste GET").build())
        );

        Loan result = this.findLoan.find(1L);

        //verificação
        assertAll("Loan",
                () -> assertThat(result.getUserApp().getName(), is("teste nome")),
                () -> assertThat(result.getBooks().get(0).getTitle(), is("teste title")),
                () -> assertThat(result.getLoanTime(), is("Loan Teste GET"))
        );
    }

    @Test
    @DisplayName("Deve lançar exceção quando o emprestimo não for encontrado")
    void shouldThrowBookNotFoundException() {
        when(loanRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(LoanNotFoundException.class, () -> this.findLoan.find(1L));
    }
}
