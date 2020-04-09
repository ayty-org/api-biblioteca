package br.com.biblioteca.apibiblioteca.Loan;

import br.com.biblioteca.apibiblioteca.loan.Loan;
import br.com.biblioteca.apibiblioteca.loan.LoanRepository;
import br.com.biblioteca.apibiblioteca.loan.services.ListPageLoanImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;

import static br.com.biblioteca.apibiblioteca.Loan.builders.LoanBuilder.createLoan;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por pesquisar Loans por paginação")
public class ListPageLoanTest {

    @Mock
    private LoanRepository loanRepository;
    private ListPageLoanImpl listPageLoan;

    @BeforeEach
    public void setUp() {
        this.listPageLoan = new ListPageLoanImpl(loanRepository);
    }

    @Test
    @DisplayName("Deve retornar todos os emprestimos com paginação")
    void shouldFindAllBook() {

        when(listPageLoan.findPage(0,2)).thenReturn(new PageImpl<>(Collections.nCopies(2, createLoan().build())));

        Page<Loan> loanPage = listPageLoan.findPage(0, 2);

        //verificação
        assertAll("loan",
                () -> assertThat(loanPage.getNumber(), is(0)),
                () -> assertThat(loanPage.getSize(), is(2)),
                () -> assertThat(loanPage.getContent().get(0).getUserApp().getName(), is("teste nome")),
                () -> assertThat(loanPage.getContent().get(0).getBooks().get(0).getTitle(), is("teste title")),
                () -> assertThat(loanPage.getContent().get(0).getLoanTime(), is("50 dias")),
                () -> assertThat(loanPage.getContent().get(1).getUserApp().getName(), is("teste nome")),
                () -> assertThat(loanPage.getContent().get(1).getBooks().get(0).getTitle(), is("teste title")),
                () -> assertThat(loanPage.getContent().get(1).getLoanTime(), is("50 dias"))
        );
    }
}
