package br.com.biblioteca.apibiblioteca.domain;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data()
@EqualsAndHashCode(exclude = {"name", "age", "fone"})
@Entity
public class User_app implements Serializable {
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private int id;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String name;

    @NotNull(message = "Preenchimento obrigatório")
    private int age;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String fone;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "USER_LOAN",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "loan_id"))
    private List<Loan> loans = new ArrayList<>();

    public User_app () {
    }

}
