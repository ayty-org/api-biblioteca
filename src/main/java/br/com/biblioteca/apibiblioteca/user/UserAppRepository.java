package br.com.biblioteca.apibiblioteca.user;

import br.com.biblioteca.apibiblioteca.user.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserAppRepository extends JpaRepository<UserApp, Long> {
}
