package br.com.biblioteca.apibiblioteca.book.services;

@FunctionalInterface
public interface DeleteBookService {

    void delete(Long id);
}
