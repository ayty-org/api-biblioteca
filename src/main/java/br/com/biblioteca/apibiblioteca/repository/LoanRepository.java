package br.com.biblioteca.apibiblioteca.repository;

import br.com.biblioteca.apibiblioteca.domain.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
}
