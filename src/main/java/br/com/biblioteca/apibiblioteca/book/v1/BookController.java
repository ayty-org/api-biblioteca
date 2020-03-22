package br.com.biblioteca.apibiblioteca.book.v1;

import br.com.biblioteca.apibiblioteca.book.Book;
import br.com.biblioteca.apibiblioteca.book.BookDTO;
import br.com.biblioteca.apibiblioteca.book.serveces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/v1/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping //lista todos os livros
    public ResponseEntity<List<BookDTO>> findAll() {
        List<Book> list = bookService.findAll();
        List<BookDTO> listDto = list.stream().map(obj -> new BookDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value="/{id}") //lista livros por id
    public ResponseEntity<Book> find(@PathVariable Long id){
        Book obj = bookService.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/page") //lista todas os livros com paginação
    public ResponseEntity<Page<BookDTO>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="title") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction){
        Page<Book> list = bookService.findPage(page, linesPerPage, orderBy, direction);
        Page<BookDTO> listDTO = list.map(obj -> new BookDTO(obj));
        return ResponseEntity.ok().body(listDTO);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping //adiciona um novo Book
    public void insert(@Valid @RequestBody BookDTO objDto){
        Book book = bookService.fromDTO(objDto);
        bookService.insert(book);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PutMapping(value="/{id}") //atualizar uma Book
    public void update(@Valid @RequestBody BookDTO objDto, @PathVariable Long id){
        Book book = bookService.fromDTO(objDto);
        book.setId(id);
        bookService.update(book);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value="/{id}") //Deleta Book
    public void delete(@PathVariable Long id){
        bookService.delete(id);
    }
}
