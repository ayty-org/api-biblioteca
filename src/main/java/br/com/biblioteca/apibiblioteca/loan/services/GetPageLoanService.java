package br.com.biblioteca.apibiblioteca.loan.services;

import br.com.biblioteca.apibiblioteca.loan.Loan;
import org.springframework.data.domain.Page;

@FunctionalInterface
public interface GetPageLoanService {

    public Page<Loan> findPage();
}
