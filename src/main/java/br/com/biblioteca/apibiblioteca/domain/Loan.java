package br.com.biblioteca.apibiblioteca.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(exclude = {"books", "loan_time"})
@Entity
public class Loan implements Serializable {
    private static final long serialVersionUID = 1L;

    //private User user;

    @NotEmpty(message = "Preenchimento obrigatório")
    @OneToMany(mappedBy="books", cascade= CascadeType.ALL)
    private List<Book> books;

    @NotEmpty(message = "Preenchimento obrigatório")
    private int loan_time;

    public Loan (){}

}
