package br.com.biblioteca.apibiblioteca.UserApp.builders;

import br.com.biblioteca.apibiblioteca.user.UserApp;

public class UserAppBuilder {

    public static UserApp.Builder createUserApp(){
        return UserApp
                .builder()
                .name("teste nome")
                .age(20)
                .fone("teste fone");
    }
}
