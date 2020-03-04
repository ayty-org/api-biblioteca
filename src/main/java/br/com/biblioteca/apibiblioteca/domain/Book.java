package br.com.biblioteca.apibiblioteca.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(exclude = {"title", "resume", "isbn", "author", "year_book"})
@Entity
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotEmpty(message = "Preenchimento obrigatório")
    private String title; //titulo

    //@NotEmpty(message = "Preenchimento obrigatório")
    private String resume; //resumo

    //@NotEmpty(message = "Preenchimento obrigatório")
    private String isbn;

    //@NotEmpty(message = "Preenchimento obrigatório")
    private String author; //autor

    //@JsonFormat(pattern = "yyyy-MM-dd")
    private Date year_book; //ano

    public  Book () {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getYear_book() {
        return year_book;
    }

    public void setYear_book(Date year_book) {
        this.year_book = year_book;
    }
}
