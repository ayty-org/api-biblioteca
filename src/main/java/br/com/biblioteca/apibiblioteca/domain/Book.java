package br.com.biblioteca.apibiblioteca.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data()
@EqualsAndHashCode(exclude = {"title", "resume", "isbn", "author", "year_book"})
@Entity
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String title; //titulo

    @NotEmpty(message = "Preenchimento obrigatório")
    private String resume; //resumo

    @NotEmpty(message = "Preenchimento obrigatório")
    private String isbn;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String author; //autor

    @NotNull(message = "Preenchimento obrigatório")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date year_book; //ano

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "BOOK_LOAN",
            joinColumns = @JoinColumn(name = "loan_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Loan> loan = new ArrayList<>();

    public  Book () {}


}
