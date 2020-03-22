package br.com.biblioteca.apibiblioteca.book;

import br.com.biblioteca.apibiblioteca.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
