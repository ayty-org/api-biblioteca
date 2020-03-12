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
    private Long id;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String name;

    @NotNull(message = "Preenchimento obrigatório")
    private int age;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String fone;

    @JsonIgnore
    @OneToMany(mappedBy = "user_app")
    private List<Loan> loans = new ArrayList<>();

    public User_app () {
    }

}
