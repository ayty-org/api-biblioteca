package br.com.biblioteca.apibiblioteca.loan.services;

import br.com.biblioteca.apibiblioteca.loan.Loan;
import br.com.biblioteca.apibiblioteca.loan.LoanDTO;
import br.com.biblioteca.apibiblioteca.loan.LoanRepository;
import br.com.biblioteca.apibiblioteca.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class LoanService implements GetLoanService, GetAllLoanService,GetPageLoanService,PostLoanService,PutLoanService,DeleteLoanService {

    @Autowired
    private LoanRepository loanRepository;

    public Loan find (Long id){
        Optional<Loan> obj = loanRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Loan.class.getName()));
    }

    public List<Loan> findAll() {
        return loanRepository.findAll();
    }

    public Page<Loan> findPage(){
        PageRequest pageRequest = PageRequest.of(0, 24 , Direction.valueOf("ASC"), "loanTime");
        return loanRepository.findAll(pageRequest);
    }

    public void insert(Loan obj){
        loanRepository.save(obj);
    }

    public void update (Loan obj){

        Loan newObj = find(obj.getId());

        newObj.setId(obj.getId());
        newObj.setLoanTime(obj.getLoanTime());

        loanRepository.save(newObj);
    }

    public void delete(Long id){
        find(id);
        loanRepository.deleteById(id);
    }

    public Loan fromDTO(LoanDTO loanDTO){
        return new Loan(loanDTO.getId(),loanDTO.getUserApp(),loanDTO.getBooks(),loanDTO.getLoanTime());
    }

}
