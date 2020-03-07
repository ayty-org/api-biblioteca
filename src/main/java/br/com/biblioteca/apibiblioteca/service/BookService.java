package br.com.biblioteca.apibiblioteca.service;

import br.com.biblioteca.apibiblioteca.domain.Book;
import br.com.biblioteca.apibiblioteca.repository.BookRepository;
import br.com.biblioteca.apibiblioteca.service.exception.DataIntegrityException;
import br.com.biblioteca.apibiblioteca.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository repo;

    public Book find (Long id) throws ObjectNotFoundException{
        Optional<Book> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(      //estudar como funciona esse retorno
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Book.class.getName()));
    }

    public Book insert(Book obj){ //Insere um livro no banco
        return repo.save(obj);
    }

    public Book update (Book obj){
        Book newObj = find(obj.getId());
        //updateData(newObj,obj);
        return repo.save(obj);
    }

    //private void updateData(Book newObj, Book obj) {
    //    newObj.setNome(obj.getNome());
    //}

    public void delete(Long id){
        find(id);
        try {
            repo.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivevel excluir uma User que possui dependências");
        }
    }

    public List<Book> findAll() {
        return repo.findAll();
    }

    public Page<Book> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage , Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }
}
