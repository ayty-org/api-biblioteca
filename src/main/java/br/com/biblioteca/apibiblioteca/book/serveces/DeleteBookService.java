package br.com.biblioteca.apibiblioteca.book.serveces;

@FunctionalInterface
public interface DeleteBookService {

    public void delete(Long id);
}
