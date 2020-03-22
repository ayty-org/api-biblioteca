package br.com.biblioteca.apibiblioteca.dto;

import br.com.biblioteca.apibiblioteca.domain.Book;
import br.com.biblioteca.apibiblioteca.domain.Loan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class BookDTO  implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String title; //titulo

    private String resume; //resumo

    private String isbn;

    private String author; //autor

    private Date yearBook; //ano

    public BookDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.resume = book.getResume();
        this.isbn = book.getIsbn();
        this.author = book.getAuthor();
        this.yearBook = book.getYearBook();
    }
}
