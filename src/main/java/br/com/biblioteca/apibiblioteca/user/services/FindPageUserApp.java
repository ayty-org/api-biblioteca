package br.com.biblioteca.apibiblioteca.user.services;

import br.com.biblioteca.apibiblioteca.user.UserApp;
import org.springframework.data.domain.Page;

@FunctionalInterface
public interface FindPageUserApp {

    Page<UserApp> findPage();
}
