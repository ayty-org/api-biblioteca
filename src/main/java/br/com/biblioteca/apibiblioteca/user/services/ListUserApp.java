package br.com.biblioteca.apibiblioteca.user.services;

import br.com.biblioteca.apibiblioteca.user.UserApp;

import java.util.List;

@FunctionalInterface
public interface ListUserApp {

    List<UserApp> findAll();
}
