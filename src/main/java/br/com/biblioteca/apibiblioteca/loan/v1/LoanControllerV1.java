package br.com.biblioteca.apibiblioteca.loan.v1;

import br.com.biblioteca.apibiblioteca.loan.Loan;
import br.com.biblioteca.apibiblioteca.loan.LoanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/v1/api/loan")
public class LoanControllerV1 {

    @Autowired
    private LoanService loanService;

    @GetMapping //lista todos os emprestimos
    public List<LoanDTO> findAll() {
        List<Loan> list = loanService.findAll();
        List<LoanDTO> listDto = list.stream().map(obj -> new LoanDTO(obj)).collect(Collectors.toList());
        return listDto;
    }

    @GetMapping(value="/{id}") //lista emprestimos por id
    public Loan find(@PathVariable Long id){
        Loan loan = loanService.find(id);
        return loan;
    }

    @GetMapping(value = "/page") //lista todas os emprestimos com paginação
    public Page<LoanDTO> findPage(){
        Page<Loan> list = loanService.findPage();
        Page<LoanDTO> listDto = list.map(obj -> new LoanDTO(obj));
        return listDto;
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
