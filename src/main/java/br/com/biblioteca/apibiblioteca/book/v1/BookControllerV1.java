package br.com.biblioteca.apibiblioteca.book.v1;

import br.com.biblioteca.apibiblioteca.book.Book;
import br.com.biblioteca.apibiblioteca.book.BookDTO;
import br.com.biblioteca.apibiblioteca.book.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/v1/api/book")
public class BookControllerV1 {

    private final FindBookImpl findBookImpl;
    private final FindAllBookImpl findAllBookImpl;
    private final FindPageBookImpl findPageBookImpl;
    private final SaveBookImpl saveBookImpl;
    private final UpdateBookImpl updateBookImpl;
    private final DeleteBookImpl deleteBookImpl;

    @GetMapping(value="/{id}") //lista livros por id
    public BookDTO find(@PathVariable Long id){
        return BookDTO.from(findBookImpl.find(id));
    }

    @GetMapping //lista todos os livros
    public List<BookDTO> findAll() {
        return BookDTO.fromAll(findAllBookImpl.findAll());
    }

    @GetMapping(params = { "page", "size" }) //lista todas os livros com paginação
    public Page<BookDTO> findPage(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
        return BookDTO.fromPage(findPageBookImpl.findPage(page,size));
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping //adiciona um novo Book
    public void insert(@Valid @RequestBody BookDTO bookDTO){
        saveBookImpl.insert(Book.to(bookDTO));
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PutMapping(value="/{id}") //atualizar uma Book
    public void update(@Valid @RequestBody BookDTO bookDTO, @PathVariable Long id){
        updateBookImpl.update(Book.to(bookDTO),id);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value="/{id}") //Deleta Book
    public void delete(@PathVariable Long id){
        deleteBookImpl.delete(id);
    }
}
