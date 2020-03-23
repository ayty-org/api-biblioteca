package br.com.biblioteca.apibiblioteca.user.services;

import br.com.biblioteca.apibiblioteca.user.UserApp;

@FunctionalInterface
public interface PutUserAppService {

    public void update (UserApp userApp);
}
