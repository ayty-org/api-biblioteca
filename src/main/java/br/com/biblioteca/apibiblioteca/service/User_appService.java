package br.com.biblioteca.apibiblioteca.service;

import br.com.biblioteca.apibiblioteca.domain.User_app;
import br.com.biblioteca.apibiblioteca.repository.User_appRepository;
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
public class User_appService {

    @Autowired
    User_appRepository repository;

    public User_app find (Long id) throws ObjectNotFoundException{
        Optional<User_app> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + User_app.class.getName()));
    }

    public Page<User_app> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage , Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public User_app insert(User_app obj){ //Insere um livro no banco
        return repository.save(obj);
    }

    public User_app update (User_app obj){ //atualiza um book
        User_app newObj = find(obj.getId());
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
            throw new DataIntegrityException("Não é possivevel excluir um user que possui dependências");
        }
    }

    public List<User_app> findAll() {
        return repository.findAll();
    }
}
