package br.com.biblioteca.apibiblioteca.book;

import br.com.biblioteca.apibiblioteca.book.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByPrice(double price, Pageable pageable);

}
