package br.com.biblioteca.apibiblioteca.loan.v1;

import br.com.biblioteca.apibiblioteca.loan.Loan;
import br.com.biblioteca.apibiblioteca.loan.LoanDTO;
import br.com.biblioteca.apibiblioteca.loan.services.DeleteLoan;
import br.com.biblioteca.apibiblioteca.loan.services.GetLoan;
import br.com.biblioteca.apibiblioteca.loan.services.ListLoan;
import br.com.biblioteca.apibiblioteca.loan.services.ListPageLoan;
import br.com.biblioteca.apibiblioteca.loan.services.SaveLoan;
import br.com.biblioteca.apibiblioteca.loan.services.UpdateLoan;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api/loan")
public class LoanControllerV1 {

    private final GetLoan getLoan;
    private final ListLoan listLoan;
    private final ListPageLoan listPageLoan;
    private final SaveLoan saveLoan;
    private final UpdateLoan updateLoan;
    private final DeleteLoan deleteLoan;

    @GetMapping(value = "/{id}") //lista emprestimos por id
    public LoanDTO find(@PathVariable Long id) {
        return LoanDTO.from(getLoan.find(id));
    }

    @GetMapping //lista todos os emprestimos
    public List<LoanDTO> findAll() {
        return LoanDTO.fromAll(listLoan.findAll());
    }

    @GetMapping(params = {"page", "size"}) //lista todas os emprestimos com paginação
    public Page<LoanDTO> findPage(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return LoanDTO.fromPage(listPageLoan.findPage(page, size));
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping() //adiciona um emprestimo Book
    public void insert(@Valid @RequestBody LoanDTO loanDTO) {
        saveLoan.insert(Loan.to(loanDTO));
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}") //atualizar uma emprestimo
    public void update(@Valid @RequestBody LoanDTO loanDTO, @PathVariable Long id) {
        updateLoan.update(Loan.to(loanDTO), id);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}") //Deleta emprestimo
    public void delete(@PathVariable Long id) {
        deleteLoan.delete(id);
    }
}
