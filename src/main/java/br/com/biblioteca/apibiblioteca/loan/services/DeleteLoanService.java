package br.com.biblioteca.apibiblioteca.loan.services;

@FunctionalInterface
public interface DeleteLoanService {

    void delete(Long id);
}
