package br.com.biblioteca.apibiblioteca.resource;

import br.com.biblioteca.apibiblioteca.domain.Book;
import br.com.biblioteca.apibiblioteca.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javassist.tools.rmi.ObjectNotFoundException;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value="/book")
public class BookResources {

    @Autowired
    private BookService service;

    @RequestMapping(value="/{id}", method = RequestMethod.GET) //lista livros por id
    public ResponseEntity<Book> find(@PathVariable Long id){
        Book obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.GET) //lista todos os usuário
    public ResponseEntity<List<Book>> findPage() {
        List<Book> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET) //lista todas os usuários
    public ResponseEntity<Page<Book>> findAll(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="nome") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction){
        Page<Book> list = service.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(method=RequestMethod.POST) //adiciona um novo usuário
    public ResponseEntity<Void> insert(@Valid @RequestBody Book obj){
        service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value="/{id}",method=RequestMethod.PUT) //atualizar uma usuário
    public ResponseEntity<Void> update(@Valid @RequestBody Book obj, @PathVariable Integer id) throws ObjectNotFoundException{
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE) //Deleta usuário
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ObjectNotFoundException {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
