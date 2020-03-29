package br.com.biblioteca.apibiblioteca.loan.services;

import br.com.biblioteca.apibiblioteca.loan.Loan;
import br.com.biblioteca.apibiblioteca.loan.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateLoanImpl implements UpdateLoan {

    private final LoanRepository loanRepository;
    private final FindLoanImpl findLoan;

    @Override
    public void update (Loan obj){

        Loan newObj = findLoan.find(obj.getId());

        newObj.setId(obj.getId());
        newObj.setLoanTime(obj.getLoanTime());

        loanRepository.save(newObj);
    }
}
