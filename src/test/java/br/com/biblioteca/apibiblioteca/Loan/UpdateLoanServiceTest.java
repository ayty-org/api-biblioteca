package br.com.biblioteca.apibiblioteca.Loan;

import br.com.biblioteca.apibiblioteca.loan.Loan;
import br.com.biblioteca.apibiblioteca.loan.LoanRepository;
import br.com.biblioteca.apibiblioteca.loan.services.UpdateLoanServiceImpl;
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
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por atualizar Loan")
public class UpdateLoanServiceTest {

    @Mock
    private LoanRepository loanRepository;
    private UpdateLoanServiceImpl updateLoan;

    @BeforeEach
    public void setUp() {
        this.updateLoan = new UpdateLoanServiceImpl(loanRepository);
    }

    @Test
    @DisplayName("Deve atualizar um Loan")
    void shouldUpdateLaon() {

        when(loanRepository.findById(1L)).thenReturn(Optional.of(createLoan().id(1L).build()));

        updateLoan.update(createLoan().loanTime("teste update").build(), 1L);

        //preparação
        ArgumentCaptor<Loan> captorLoan = ArgumentCaptor.forClass(Loan.class);
        verify(loanRepository).save(captorLoan.capture());

        Loan result = captorLoan.getValue();

        //verificação
        assertAll("Loan",
                () -> assertThat(result.getUserApp().getName(), is("teste nome")),
                () -> assertThat(result.getBooks().get(0).getTitle(), is("teste title")),
                () -> assertThat(result.getLoanTime(), is("teste update"))
        );


    }
}

