package br.com.biblioteca.apibiblioteca.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

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

    @NotEmpty(message = "Preenchimento obrigatório")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date year_book; //ano

    public  Book () {}


}
