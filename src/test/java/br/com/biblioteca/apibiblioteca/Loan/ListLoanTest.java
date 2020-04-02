package br.com.biblioteca.apibiblioteca.Loan;

import br.com.biblioteca.apibiblioteca.loan.Loan;
import br.com.biblioteca.apibiblioteca.loan.LoanRepository;
import br.com.biblioteca.apibiblioteca.loan.services.ListLoanImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static br.com.biblioteca.apibiblioteca.Loan.builders.LoanBuilder.createLoan;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por pesquisar todos os Loan")
public class ListLoanTest {

    @Mock
    private LoanRepository loanRepository;
    private ListLoanImpl findAllLoan;

    @BeforeEach
    public void setUp() {
        this.findAllLoan = new ListLoanImpl(loanRepository);
    }

    @Test
    @DisplayName("Deve retornar todos os emprestimos")
    void shouldFindAllLoan() {

        when(loanRepository.findAll()).thenReturn(
                Stream.of(createLoan().loanTime("50 dias").build(),
                        createLoan().loanTime("60 dias").build(),
                        createLoan().loanTime("35 dias").build()).collect(Collectors.toList())
        );

        List<Loan> result = this.findAllLoan.findAll();

        assertAll("Loan",
                () -> assertThat(result.size(), is(3)),
                () -> assertThat(result.get(0).getLoanTime(), is("50 dias")),
                () -> assertThat(result.get(1).getLoanTime(), is("60 dias")),
                () -> assertThat(result.get(2).getLoanTime(), is("35 dias"))
        );
    }
}
