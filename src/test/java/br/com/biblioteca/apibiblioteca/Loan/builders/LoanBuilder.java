package br.com.biblioteca.apibiblioteca.Loan.builders;

import br.com.biblioteca.apibiblioteca.book.Book;
import br.com.biblioteca.apibiblioteca.loan.Loan;
import br.com.biblioteca.apibiblioteca.user.UserApp;

import java.util.ArrayList;
import java.util.List;

public class LoanBuilder {

    public static Loan.Builder createLoan(){
        return Loan
                .builder()
                .userApp(userApp())
                .books(listBook())
                .loanTime("50 dias");
    }

    public static UserApp userApp(){
        return new UserApp("teste nome",21,"46356357");
    }

    public static List<Book> listBook(){
        Book bookTest01 = new Book("teste title","teste resume","teste isbn","teste author");
        List<Book> book01 = new ArrayList<>();
        book01.add(bookTest01);
        return book01;
    }
}
