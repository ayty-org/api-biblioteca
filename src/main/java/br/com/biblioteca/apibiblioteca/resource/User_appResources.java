package br.com.biblioteca.apibiblioteca.resource;


import br.com.biblioteca.apibiblioteca.domain.Loan;
import br.com.biblioteca.apibiblioteca.domain.User_app;
import br.com.biblioteca.apibiblioteca.service.User_appService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value="/v1/api/user")
public class User_appResources {

    @Autowired
    private User_appService service;

    @GetMapping //lista todos os usuários
    public ResponseEntity<List<User_app>> findAll() {
        List<User_app> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value="/{id}") //lista usuário por id
    public ResponseEntity<User_app> find(@PathVariable Long id){
        User_app obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/page") //lista todas os usuários com paginação
    public ResponseEntity<Page<User_app>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="name") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction){
        Page<User_app> list = service.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping() //adiciona um usuário Book
    public ResponseEntity<Void> insert(@Valid @RequestBody User_app obj){
        service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody User_app obj, @PathVariable Long id) throws ObjectNotFoundException {
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value="/{id}") //Deleta usuário
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ObjectNotFoundException {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
