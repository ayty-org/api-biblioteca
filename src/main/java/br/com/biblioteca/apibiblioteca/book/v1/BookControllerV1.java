package br.com.biblioteca.apibiblioteca.book.v1;

import br.com.biblioteca.apibiblioteca.book.Book;
import br.com.biblioteca.apibiblioteca.book.BookDTO;
import br.com.biblioteca.apibiblioteca.book.services.DeleteBook;
import br.com.biblioteca.apibiblioteca.book.services.GetBook;
import br.com.biblioteca.apibiblioteca.book.services.ListBook;
import br.com.biblioteca.apibiblioteca.book.services.ListPageBook;
import br.com.biblioteca.apibiblioteca.book.services.SaveBook;
import br.com.biblioteca.apibiblioteca.book.services.UpdateBook;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api/book")
public class BookControllerV1 {

    private final GetBook getBook;
    private final ListBook listBook;
    private final ListPageBook listPageBook;
    private final SaveBook saveBook;
    private final UpdateBook updateBook;
    private final DeleteBook deleteBook;

    @GetMapping(value = "/{id}") //lista livros por id
    public BookDTO find(@PathVariable Long id) {
        return BookDTO.from(getBook.find(id));
    }

    @GetMapping //lista todos os livros
    public List<BookDTO> findAll() {
        return BookDTO.fromAll(listBook.findAll());
    }

    @GetMapping(params = {"page", "size"}) //lista todas os livros com paginação
    public Page<BookDTO> findPage(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return BookDTO.fromPage(listPageBook.findPage(page, size));
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping //adiciona um novo Book
    public void insert(@Valid @RequestBody BookDTO bookDTO) {
        saveBook.insert(Book.to(bookDTO));
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}") //atualizar uma Book
    public void update(@Valid @RequestBody BookDTO bookDTO, @PathVariable Long id) {
        updateBook.update(Book.to(bookDTO), id);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}") //Deleta Book
    public void delete(@PathVariable Long id) {
        deleteBook.delete(id);
    }
}