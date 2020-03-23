package br.com.biblioteca.apibiblioteca.loan.services;

@FunctionalInterface
public interface DeleteLoanService {

    public void delete(Long id);
}
