package br.com.biblioteca.apibiblioteca.loan.services;

import br.com.biblioteca.apibiblioteca.loan.Loan;

@FunctionalInterface
public interface UpdateLoan {

    public void update (Loan loan);
}
