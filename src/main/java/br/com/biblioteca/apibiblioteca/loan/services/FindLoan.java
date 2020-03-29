package br.com.biblioteca.apibiblioteca.loan.services;

import br.com.biblioteca.apibiblioteca.loan.Loan;

@FunctionalInterface
public interface FindLoan {

    public Loan find (Long id);
}
