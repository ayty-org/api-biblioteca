package br.com.biblioteca.apibiblioteca.loan.services;

import br.com.biblioteca.apibiblioteca.loan.Loan;

import java.util.List;

@FunctionalInterface
public interface GetAllLoanService {

    public List<Loan> findAll();
}
