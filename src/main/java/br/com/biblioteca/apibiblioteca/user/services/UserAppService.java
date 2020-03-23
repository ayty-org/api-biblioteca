package br.com.biblioteca.apibiblioteca.user.services;

import br.com.biblioteca.apibiblioteca.book.serveces.PostBookService;
import br.com.biblioteca.apibiblioteca.loan.services.GetAllLoanService;
import br.com.biblioteca.apibiblioteca.loan.services.GetPageLoanService;
import br.com.biblioteca.apibiblioteca.user.UserApp;
import br.com.biblioteca.apibiblioteca.user.UserAppDTO;
import br.com.biblioteca.apibiblioteca.user.UserAppRepository;
import br.com.biblioteca.apibiblioteca.exceptions.DataIntegrityException;
import br.com.biblioteca.apibiblioteca.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAppService implements GetUserAppService, GetAllUserAppService, GetPageUserAppService, PostUserAppService,PutUserAppService,DeleteUserAppService {

    @Autowired
    UserAppRepository repository;

    public UserApp find (Long id){
        Optional<UserApp> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + UserApp.class.getName()));
    }

    public List<UserApp> findAll() {
        return repository.findAll();
    }

    public Page<UserApp> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage , Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public void insert(UserApp obj){ //Insere um livro no banco
        repository.save(obj);
    }

    public void update (UserApp obj){ //atualiza um book
        UserApp newObj = find(obj.getId());
        newObj.setId(obj.getId());
        newObj.setName(obj.getName());
        newObj.setAge(obj.getAge());
        newObj.setFone(obj.getFone());
        repository.save(newObj);
    }

    public void delete(Long id) throws DataIntegrityException{
        find(id);
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivevel excluir um User que possui emprestimos");
        }
    }

    public UserApp fromDTO(UserAppDTO userAppDTO){
        return new UserApp(userAppDTO.getId(),userAppDTO.getName(),userAppDTO.getAge(),userAppDTO.getFone());
    }
}
