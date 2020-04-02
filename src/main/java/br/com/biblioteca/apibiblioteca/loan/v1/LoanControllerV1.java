package br.com.biblioteca.apibiblioteca.loan.v1;

import br.com.biblioteca.apibiblioteca.loan.Loan;
import br.com.biblioteca.apibiblioteca.loan.LoanDTO;
import br.com.biblioteca.apibiblioteca.loan.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api/loan")
public class LoanControllerV1 {

    private final FindLoanImpl findLoanImpl;
    private final FindAllLoanImpl findAllLoanImpl;
    private final FindPageLoanImpl findPageLoanImpl;
    private final SaveLoanImpl saveLoanImpl;
    private final UpdateLoanImpl updateLoanImpl;
    private final DeleteLoanImpl deleteLoanImpl;

    @GetMapping(value = "/{id}") //lista emprestimos por id
    public LoanDTO find(@PathVariable Long id) {
        return LoanDTO.from(findLoanImpl.find(id));
    }

    @GetMapping //lista todos os emprestimos
    public List<LoanDTO> findAll() {
        return LoanDTO.fromAll(findAllLoanImpl.findAll());
    }

    @GetMapping(params = { "page", "size" }) //lista todas os emprestimos com paginação
    public Page<LoanDTO> findPage(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return LoanDTO.fromPage(findPageLoanImpl.findPage(page,size));
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping() //adiciona um emprestimo Book
    public void insert(@Valid @RequestBody LoanDTO loanDTO) {
        saveLoanImpl.insert(Loan.to(loanDTO));
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}") //atualizar uma emprestimo
    public void update(@Valid @RequestBody LoanDTO loanDTO, @PathVariable Long id) {
        updateLoanImpl.update(Loan.to(loanDTO), id);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}") //Deleta emprestimo
    public void delete(@PathVariable Long id) {
        deleteLoanImpl.delete(id);
    }
}
