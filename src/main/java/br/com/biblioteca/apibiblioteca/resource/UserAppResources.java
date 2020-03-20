package br.com.biblioteca.apibiblioteca.resource;

import br.com.biblioteca.apibiblioteca.domain.UserApp;
import br.com.biblioteca.apibiblioteca.service.UserAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/v1/api/user")
public class UserAppResources {

    @Autowired
    private UserAppService service;

    @GetMapping //lista todos os usuários
    public ResponseEntity<List<UserApp>> findAll() {
        List<UserApp> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value="/{id}") //lista usuário por id
    public ResponseEntity<UserApp> find(@PathVariable Long id){
        UserApp obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/page") //lista todas os usuários com paginação
    public ResponseEntity<Page<UserApp>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="name") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction){
        Page<UserApp> list = service.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(list);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping() //adiciona um usuário Book
    public void insert(@Valid @RequestBody UserApp obj){
        service.insert(obj);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PutMapping(value="/{id}")
    public void update(@Valid @RequestBody UserApp obj, @PathVariable Long id){
        obj.setId(id);
        service.update(obj);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value="/{id}") //Deleta usuário
    public void delete(@PathVariable Long id){
        service.delete(id);
    }


}
