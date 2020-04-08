package br.com.biblioteca.apibiblioteca.loan.services;

import br.com.biblioteca.apibiblioteca.exceptions.BookNotFoundException;
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
        System.out.println(id);
        Loan loan = loanRepository.findById(id).orElseThrow(BookNotFoundException::new);

        loan.setUserApp(obj.getUserApp());
        loan.setBooks(obj.getBooks());
        loan.setLoanTime(obj.getLoanTime());
        loanRepository.save(loan);
    }
}
