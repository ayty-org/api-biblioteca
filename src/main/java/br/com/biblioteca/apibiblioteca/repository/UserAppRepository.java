package br.com.biblioteca.apibiblioteca.repository;

import br.com.biblioteca.apibiblioteca.domain.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserAppRepository extends JpaRepository<UserApp, Long> {
}
