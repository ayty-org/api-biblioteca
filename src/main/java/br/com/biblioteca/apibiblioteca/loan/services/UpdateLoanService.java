package br.com.biblioteca.apibiblioteca.loan.services;

import br.com.biblioteca.apibiblioteca.loan.Loan;

@FunctionalInterface
public interface UpdateLoanService {

    void update(Loan loan, Long id);
}