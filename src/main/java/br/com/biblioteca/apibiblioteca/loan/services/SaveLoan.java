package br.com.biblioteca.apibiblioteca.loan.services;

import br.com.biblioteca.apibiblioteca.loan.Loan;

@FunctionalInterface
public interface SaveLoan {

    void insert(Loan obj);

}
