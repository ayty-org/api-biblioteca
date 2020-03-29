package br.com.biblioteca.apibiblioteca.loan.services;

import br.com.biblioteca.apibiblioteca.loan.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteLoanImpl implements DeleteLoan {
    private final LoanRepository loanRepository;

    @Override
    public void delete(Long id) {
        loanRepository.deleteById(id);
    }
}
