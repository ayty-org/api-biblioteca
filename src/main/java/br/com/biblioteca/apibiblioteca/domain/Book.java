package br.com.biblioteca.apibiblioteca.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(exclude = {"title", "resume", "isbn", "author", "year"})
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
    @Column(unique = true)
    private String isbn;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String author; //autor

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date year; //ano

    public  Book () {}

}
