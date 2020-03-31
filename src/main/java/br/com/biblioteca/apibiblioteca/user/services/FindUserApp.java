package br.com.biblioteca.apibiblioteca.user.services;

import br.com.biblioteca.apibiblioteca.user.UserApp;

@FunctionalInterface
public interface FindUserApp {

    UserApp find(Long id);
}
