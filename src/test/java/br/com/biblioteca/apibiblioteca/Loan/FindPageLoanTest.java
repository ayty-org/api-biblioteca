package br.com.biblioteca.apibiblioteca.Loan;

import br.com.biblioteca.apibiblioteca.loan.LoanRepository;
import br.com.biblioteca.apibiblioteca.loan.services.FindPageLoanImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por pesquisar Loans por paginação")
public class FindPageLoanTest {

    @Mock
    private LoanRepository loanRepository;
    private FindPageLoanImpl findPageLoan;

    @BeforeEach
    public void setUp() {
        this.findPageLoan = new FindPageLoanImpl(loanRepository);
    }
}
