package br.com.biblioteca.apibiblioteca.resource;

import br.com.biblioteca.apibiblioteca.domain.Book;
import br.com.biblioteca.apibiblioteca.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/v1/api/book")
public class BookResources {

    @Autowired
    private BookService bookService;

    @GetMapping //lista todos os livros
    public ResponseEntity<List<Book>> findAll() {
        List<Book> list = bookService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value="/{id}") //lista livros por id
    public ResponseEntity<Book> find(@PathVariable Long id){
        Book obj = bookService.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/page") //lista todas os livros com paginação
    public ResponseEntity<Page<Book>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="title") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction){
        Page<Book> list = bookService.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(list);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping //adiciona um novo Book
    public void insert(@Valid @RequestBody Book obj){
        bookService.insert(obj);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PutMapping(value="/{id}") //atualizar uma Book
    public void update(@Valid @RequestBody Book obj, @PathVariable Long id){
        obj.setId(id);
        bookService.update(obj);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value="/{id}") //Deleta Book
    public void delete(@PathVariable Long id){
        bookService.delete(id);
    }
}
