package br.com.biblioteca.apibiblioteca.loan.services;

import br.com.biblioteca.apibiblioteca.exceptions.LoanNotFoundException;
import br.com.biblioteca.apibiblioteca.loan.Loan;
import br.com.biblioteca.apibiblioteca.loan.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetLoanImpl implements GetLoan {

    private final LoanRepository loanRepository;

    @Override
    public Loan find(Long id) {
        return loanRepository.findById(id).orElseThrow(LoanNotFoundException::new);
    }
}
