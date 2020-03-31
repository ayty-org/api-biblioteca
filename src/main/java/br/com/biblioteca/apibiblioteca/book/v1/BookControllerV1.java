package br.com.biblioteca.apibiblioteca.book.v1;

import br.com.biblioteca.apibiblioteca.book.Book;
import br.com.biblioteca.apibiblioteca.book.BookDTO;
import br.com.biblioteca.apibiblioteca.book.services.DeleteBookImpl;
import br.com.biblioteca.apibiblioteca.book.services.FindAllBookImpl;
import br.com.biblioteca.apibiblioteca.book.services.FindBookImpl;
import br.com.biblioteca.apibiblioteca.book.services.FindPageBookImpl;
import br.com.biblioteca.apibiblioteca.book.services.SaveBookImpl;
import br.com.biblioteca.apibiblioteca.book.services.UpdateBookImpl;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "/page") //lista todas os livros com paginação
    public Page<BookDTO> findPage(){
        return BookDTO.fromPage(findPageBookImpl.findPage());
    }


    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping //adiciona um novo Book
    public void insert(@Valid @RequestBody BookDTO bookDTO){
        saveBookImpl.insert(Book.to(bookDTO));
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PutMapping(value="/{id}") //atualizar uma Book
    public void update(@Valid @RequestBody BookDTO bookDTO, @PathVariable Long id){
        updateBookImpl.update(Book.to(bookDTO));
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value="/{id}") //Deleta Book
    public void delete(@PathVariable Long id){
        deleteBookImpl.delete(id);
    }
}
