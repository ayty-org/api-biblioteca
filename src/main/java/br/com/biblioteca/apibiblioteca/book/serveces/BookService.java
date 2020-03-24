package br.com.biblioteca.apibiblioteca.book.serveces;

import br.com.biblioteca.apibiblioteca.book.Book;
import br.com.biblioteca.apibiblioteca.book.BookDTO;
import br.com.biblioteca.apibiblioteca.book.BookRepository;
import br.com.biblioteca.apibiblioteca.exceptions.DataIntegrityException;
import br.com.biblioteca.apibiblioteca.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements GetBookService, GetAllBookService, GetPageBookService,PostBookService,PutBookService,DeleteBookService {

    @Autowired
    private BookRepository bookRepository;

    public Book find (Long id) throws ObjectNotFoundException {
        Optional<Book> obj = bookRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Book.class.getName()));
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Page<Book> findPage(){
        PageRequest pageRequest = PageRequest.of(0, 24 , Direction.valueOf("ASC"), "title");
        return bookRepository.findAll(pageRequest);
    }

    public void insert(Book obj){ //Insere um livro no banco
        bookRepository.save(obj);
    }

    public void update (Book obj){ //atualiza um book
        Book newBook = find(obj.getId());
        newBook.setId(obj.getId());
        newBook.setTitle(obj.getTitle());
        newBook.setResume(obj.getResume());
        newBook.setIsbn(obj.getIsbn());
        newBook.setAuthor(obj.getAuthor());
        newBook.setYearBook(obj.getYearBook());
        bookRepository.save(newBook);
    }

    public void delete(Long id) throws DataIntegrityException{
        find(id);
        try {
            bookRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivevel excluir uma Book que possui emprestimos");
        }
    }

    public Book fromDTO(BookDTO bookDTO){
        return new Book(bookDTO.getId(),bookDTO.getTitle(),bookDTO.getResume(),bookDTO.getIsbn(),bookDTO.getAuthor(),bookDTO.getYearBook());
    }
}
