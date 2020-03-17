package br.com.biblioteca.apibiblioteca.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
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
    private Date yearBook; //ano

    @JsonIgnore
    @ManyToMany(mappedBy="books", fetch = FetchType.LAZY)
    private List<Loan> loan = new ArrayList<>();


    public Book(String title, String resume, String isbn, String author, Date yearBook) {
        this.title = title;
        this.resume = resume;
        this.isbn = isbn;
        this.author = author;
        this.yearBook = yearBook;
    }
}
