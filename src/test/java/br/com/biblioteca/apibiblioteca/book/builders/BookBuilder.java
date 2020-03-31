package br.com.biblioteca.apibiblioteca.book.builders;

import br.com.biblioteca.apibiblioteca.book.Book;

public class BookBuilder {

    public static Book.Builder createBook(){
        return Book
                .builder()
                .author("teste author")
                .resume("teste resume")
                .isbn("teste isbn")
                .title("teste title");
    }
}
