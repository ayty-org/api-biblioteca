package br.com.biblioteca.apibiblioteca.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class BookDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty
    private String title; //titulo

    @Size(max = 500)
    private String resume; //resumo

    @NotEmpty
    private String isbn;

    @NotEmpty
    private String author; //autor

    @NotNull
    private Date yearBook; //ano

    public static BookDTO from(Book book) {
        return BookDTO
                .builder()
                .id(book.getId())
                .title(book.getTitle())
                .resume(book.getResume())
                .isbn(book.getIsbn())
                .author(book.getAuthor())
                .yearBook(book.getYearBook())
                .build();
    }

    public static List<BookDTO> fromAll(List<Book> books) {
        return books.stream().map(BookDTO::from).collect(Collectors.toList());
    }

    public static Page<BookDTO> fromPage(Page<Book> pages) {
        return pages.map(BookDTO::from);
    }
}
