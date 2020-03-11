package br.com.biblioteca.apibiblioteca.service;

import br.com.biblioteca.apibiblioteca.domain.User_app;
import br.com.biblioteca.apibiblioteca.repository.User_appRepository;
import br.com.biblioteca.apibiblioteca.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class User_appService {

    @Autowired
    User_appRepository repository;

    public User_app findUser(Long id) throws ObjectNotFoundException {
        Optional<User_app> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não foi encontrado! id:" + id + ", tipo " + User_app.class.getName()
        ));
    }

    public List<User_app> findAll(){
        return repository.findAll();
    }

    public User_app insertUser(User_app newUser){
        return repository.save(newUser);
    }

    public User_app updateUser(User_app userAtt){
        return repository.save(userAtt);
    }

    public void deleteUser(User_app user_appDel){
        repository.delete(user_appDel);
    }

}
