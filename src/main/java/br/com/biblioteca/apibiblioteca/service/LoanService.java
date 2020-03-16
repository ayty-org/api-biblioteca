package br.com.biblioteca.apibiblioteca.service;

import br.com.biblioteca.apibiblioteca.domain.Book;
import br.com.biblioteca.apibiblioteca.domain.Loan;
import br.com.biblioteca.apibiblioteca.domain.User_app;
import br.com.biblioteca.apibiblioteca.repository.BookRepository;
import br.com.biblioteca.apibiblioteca.repository.LoanRepository;
import br.com.biblioteca.apibiblioteca.repository.User_appRepository;
import br.com.biblioteca.apibiblioteca.service.exception.DataIntegrityException;
import br.com.biblioteca.apibiblioteca.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepo;

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private User_appRepository userRepo;

    public Loan find (Long id) throws ObjectNotFoundException {
        Optional<Loan> obj = loanRepo.findById(id);
        return obj.orElse(null);
    }

    public Loan insert(Loan obj){
        obj = loanRepo.save(obj);

        User_app user = obj.getUser_app();
        user.getLoans().add(obj);

        userRepo.save(user);

        return  obj;
    }

    public Loan update (Loan obj){

        Loan newObj = find(obj.getId());

        newObj.setId(obj.getId());
        newObj.setLoan_time(obj.getLoan_time());

        return loanRepo.save(newObj);
    }

    public void delete(Long id){
        loanRepo.deleteById(id);
    }

    public List<Loan> findAll() {
        return loanRepo.findAll();
    }

    public Page<Loan> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage , Sort.Direction.valueOf(direction), orderBy);
        return loanRepo.findAll(pageRequest);
    }

}
