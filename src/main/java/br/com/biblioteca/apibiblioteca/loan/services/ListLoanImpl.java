package br.com.biblioteca.apibiblioteca.loan.services;

import br.com.biblioteca.apibiblioteca.loan.Loan;
import br.com.biblioteca.apibiblioteca.loan.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ListLoanImpl implements ListLoan {

    private final LoanRepository loanRepository;

    @Override
    public List<Loan> findAll() {
        return loanRepository.findAll();
    }
}
