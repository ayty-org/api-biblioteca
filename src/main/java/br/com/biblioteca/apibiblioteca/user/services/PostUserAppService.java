package br.com.biblioteca.apibiblioteca.user.services;

import br.com.biblioteca.apibiblioteca.user.UserApp;

@FunctionalInterface
public interface PostUserAppService {

    public void insert(UserApp userApp);
}
