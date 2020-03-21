package br.com.biblioteca.apibiblioteca.dto;

import br.com.biblioteca.apibiblioteca.domain.Book;
import br.com.biblioteca.apibiblioteca.domain.Loan;
import br.com.biblioteca.apibiblioteca.domain.UserApp;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class LoanDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private UserApp userApp;

    private List<Book> books;

    private String loanTime;

    public LoanDTO(Loan loan) {
        this.id = loan.getId();
        this.userApp = loan.getUserApp();
        this.books = loan.getBooks();
        this.loanTime = loan.getLoanTime();
    }
}
