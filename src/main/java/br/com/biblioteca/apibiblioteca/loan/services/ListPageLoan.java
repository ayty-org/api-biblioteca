package br.com.biblioteca.apibiblioteca.loan.services;

import br.com.biblioteca.apibiblioteca.loan.Loan;
import org.springframework.data.domain.Page;

@FunctionalInterface
public interface ListPageLoan {

    Page<Loan> findPage(Integer page, Integer size);
}
