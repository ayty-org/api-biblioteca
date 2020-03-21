package br.com.biblioteca.apibiblioteca.resource;

import br.com.biblioteca.apibiblioteca.domain.Loan;
import br.com.biblioteca.apibiblioteca.dto.LoanDTO;
import br.com.biblioteca.apibiblioteca.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/v1/api/loan")
public class LoanResources {

    @Autowired
    private LoanService loanService;

    @GetMapping //lista todos os emprestimos
    public ResponseEntity<List<LoanDTO>> findAll() {
        List<Loan> list = loanService.findAll();
        List<LoanDTO> listDto = list.stream().map(obj -> new LoanDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value="/{id}") //lista emprestimos por id
    public ResponseEntity<Loan> find(@PathVariable Long id){
        Loan obj = loanService.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/page") //lista todas os emprestimos com paginação
    public ResponseEntity<Page<LoanDTO>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="id") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction){
        Page<Loan> list = loanService.findPage(page, linesPerPage, orderBy, direction);
        Page<LoanDTO> listDto = list.map(obj -> new LoanDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping() //adiciona um emprestimo Book
    public void insert(@Valid @RequestBody LoanDTO objDto){
        Loan loan = loanService.fromDTO(objDto);
        loanService.insert(loan);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PutMapping(value="/{id}") //atualizar uma emprestimo
    public void update(@Valid @RequestBody LoanDTO objDto, @PathVariable Long id){
        Loan loan = loanService.fromDTO(objDto);
        loan.setId(id);
        loanService.update(loan);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value="/{id}") //Deleta emprestimo
    public void delete(@PathVariable Long id){
        loanService.delete(id);
    }
}
