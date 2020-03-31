package br.com.biblioteca.apibiblioteca.book;

import br.com.biblioteca.apibiblioteca.loan.Loan;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder(builderClassName = "Builder")
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

    public Book(String title, String resume, String isbn, String author) {
        this.title = title;
        this.resume = resume;
        this.isbn = isbn;
        this.author = author;
    }

    public static Book to(BookDTO bookDTO) {
        return Book
                .builder()
                .id(bookDTO.getId())
                .title(bookDTO.getTitle())
                .resume(bookDTO.getResume())
                .isbn(bookDTO.getIsbn())
                .author(bookDTO.getAuthor())
                .yearBook(bookDTO.getYearBook())
                .build();
    }
}
