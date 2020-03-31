package br.com.biblioteca.apibiblioteca.loan.services;

import br.com.biblioteca.apibiblioteca.loan.Loan;
import br.com.biblioteca.apibiblioteca.loan.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FindPageLoanImpl implements FindPageLoan {

    private final LoanRepository loanRepository;

    @Override
    public Page<Loan> findPage() {
        PageRequest pageRequest = PageRequest.of(0, 24, Sort.Direction.valueOf("ASC"), "loanTime");
        return loanRepository.findAll(pageRequest);
    }
}
