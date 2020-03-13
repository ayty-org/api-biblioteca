package br.com.biblioteca.apibiblioteca.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"title", "resume", "isbn", "author", "year_book"})
@Entity
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; //titulo

    private String resume; //resumo

    private String isbn;

    private String author; //autor

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date year_book; //ano

    @JsonIgnore
    @ManyToMany(mappedBy="books")
    private List<Loan> loan = new ArrayList<>();

    public  Book () {}


}
