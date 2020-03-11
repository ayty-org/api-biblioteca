package br.com.biblioteca.apibiblioteca.repository;

import br.com.biblioteca.apibiblioteca.domain.User_app;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface User_appRepository extends JpaRepository<User_app, Long> {
}
