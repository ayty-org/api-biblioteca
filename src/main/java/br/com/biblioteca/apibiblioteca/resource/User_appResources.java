package br.com.biblioteca.apibiblioteca.resource;


import br.com.biblioteca.apibiblioteca.domain.User_app;
import br.com.biblioteca.apibiblioteca.service.User_appService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/v1/api/user")
public class User_appResources {

    @Autowired
    private User_appService service;

    @GetMapping
    public List<User_app> listUser(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public User_app getUser(@PathVariable(value="id") long id){
        return service.findUser(id);
    }

    @PostMapping
    public User_app postNewUser(@RequestBody User_app newUser){
        return service.insertUser(newUser);
    }

    @PutMapping
    public User_app updateUser(@RequestBody User_app userAtt){
        return service.updateUser(userAtt);
    }

    @DeleteMapping
    public void deleteUser(@RequestBody User_app userDel){
        service.deleteUser(userDel);
    }


}
