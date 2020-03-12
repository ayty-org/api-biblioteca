package br.com.biblioteca.apibiblioteca.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(exclude = {"books", "loan_time"})
@Entity
public class Loan implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_app_id")
    private  User_app user_app;

    @ManyToMany(mappedBy="loan")
    private List<Book> books = new ArrayList<>();

    @NotEmpty(message = "Preenchimento obrigatório")
    private String loan_time;

    public Loan (){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User_app getUser_app() {
        return user_app;
    }

    public void setUser_app(User_app user_app) {
        this.user_app = user_app;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getLoan_time() {
        return loan_time;
    }

    public void setLoan_time(String loan_time) {
        this.loan_time = loan_time;
    }
}
