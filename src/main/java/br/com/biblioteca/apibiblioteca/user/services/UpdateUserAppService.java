package br.com.biblioteca.apibiblioteca.user.services;

import br.com.biblioteca.apibiblioteca.user.UserApp;

@FunctionalInterface
public interface UpdateUserAppService {

    void update(UserApp userApp, Long id);
}
