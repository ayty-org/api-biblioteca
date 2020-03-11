package br.com.biblioteca.apibiblioteca.resource;

import br.com.biblioteca.apibiblioteca.domain.Loan;
import br.com.biblioteca.apibiblioteca.service.LoanService;
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
@RequestMapping(value="/loan")
public class LoanResources {

    @Autowired
    private LoanService service;

    @GetMapping //lista todos os emprestimos
    public ResponseEntity<List<Loan>> findAll() {
        List<Loan> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value="/{id}") //lista emprestimos por id
    public ResponseEntity<Loan> find(@PathVariable Long id){
        Loan obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/page") //lista todas os emprestimos com paginação
    public ResponseEntity<Page<Loan>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="loan_time") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction){
        Page<Loan> list = service.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping() //adiciona um emprestimo Book
    public ResponseEntity<Void> insert(@Valid @RequestBody Loan obj){
        service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value="/{id}") //atualizar uma emprestimo
    public ResponseEntity<Void> update(@Valid @RequestBody Loan obj, @PathVariable Long id) throws ObjectNotFoundException {
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value="/{id}") //Deleta emprestimo
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ObjectNotFoundException {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
