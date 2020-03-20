package br.com.biblioteca.apibiblioteca.service;

import br.com.biblioteca.apibiblioteca.domain.UserApp;
import br.com.biblioteca.apibiblioteca.repository.UserAppRepository;
import br.com.biblioteca.apibiblioteca.service.exception.DataIntegrityException;
import br.com.biblioteca.apibiblioteca.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAppService {

    @Autowired
    UserAppRepository repository;

    public UserApp find (Long id){
        Optional<UserApp> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + UserApp.class.getName()));
    }

    public Page<UserApp> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage , Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public UserApp insert(UserApp obj){ //Insere um livro no banco
        return repository.save(obj);
    }

    public UserApp update (UserApp obj){ //atualiza um book
        UserApp newObj = find(obj.getId());
        newObj.setId(obj.getId());
        newObj.setName(obj.getName());
        newObj.setAge(obj.getAge());
        newObj.setFone(obj.getFone());
        return repository.save(newObj);
    }

    public void delete(Long id){
        find(id);
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivevel excluir um user que possui emprestimos");
        }
    }

    public List<UserApp> findAll() {
        return repository.findAll();
    }
}
