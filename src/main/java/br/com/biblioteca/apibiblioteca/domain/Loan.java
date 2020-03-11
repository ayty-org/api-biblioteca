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

    @ManyToMany(mappedBy = "loans")
    private  List<User_app> user_app = new ArrayList<>();

    @OneToMany(mappedBy="loan", cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

    @NotEmpty(message = "Preenchimento obrigatório")
    private String loan_time;

    public Loan (){}

}
