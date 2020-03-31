package br.com.biblioteca.apibiblioteca.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserAppRepository extends JpaRepository<UserApp, Long> {
}
