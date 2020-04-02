package br.com.biblioteca.apibiblioteca.loan.services;

import br.com.biblioteca.apibiblioteca.exceptions.LoanNotFoundException;
import br.com.biblioteca.apibiblioteca.loan.Loan;
import br.com.biblioteca.apibiblioteca.loan.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateLoanImpl implements UpdateLoan {

    private final LoanRepository loanRepository;

    @Override
    public void update(Loan obj, Long id) {
        if (loanRepository.findById(id).isPresent()){
            obj.setId(id);
            loanRepository.save(obj);
        }throw new LoanNotFoundException();
    }
}
