package br.com.biblioteca.apibiblioteca.dto;

import br.com.biblioteca.apibiblioteca.domain.UserApp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class UserAppDTO implements Serializable {

    private Long id;

    private String name;

    private int age;

    private String fone;

    public UserAppDTO (UserApp userApp){
        this.id = userApp.getId();
        this.name = userApp.getName();
        this.age = userApp.getAge();
        this.fone = userApp.getFone();
    }



}
