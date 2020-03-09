package br.com.biblioteca.apibiblioteca.domain;



import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data()
@EqualsAndHashCode(exclude = {"name", "age", "fone"})
@Entity
public class User_app implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private int id;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String name;

    @NotEmpty(message = "Preenchimento obrigatório")
    private int age;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String fone;

    public User_app () {
    }

}
