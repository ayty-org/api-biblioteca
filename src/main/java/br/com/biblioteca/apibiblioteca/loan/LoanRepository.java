package br.com.biblioteca.apibiblioteca.loan;

import br.com.biblioteca.apibiblioteca.loan.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
}
