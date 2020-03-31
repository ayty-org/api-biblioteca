package br.com.biblioteca.apibiblioteca.loan.services;

import br.com.biblioteca.apibiblioteca.loan.Loan;
import br.com.biblioteca.apibiblioteca.loan.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SaveLoanImpl implements SaveLoan {

    private final LoanRepository loanRepository;

    @Override
    public void insert(Loan obj) {
        loanRepository.save(obj);
    }
}
