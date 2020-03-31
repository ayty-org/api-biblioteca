package br.com.biblioteca.apibiblioteca.loan;

import br.com.biblioteca.apibiblioteca.book.Book;
import br.com.biblioteca.apibiblioteca.user.UserApp;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Builder")
public class Loan implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userApp_id")
    private UserApp userApp;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "LOAN_BOOK",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "loan_id")
    )
    private List<Book> books = new ArrayList<>();

    private String loanTime;

    public Loan(UserApp userApp, List<Book> books, String loanTime) {
        this.userApp = userApp;
        this.books = books;
        this.loanTime = loanTime;
    }

}
