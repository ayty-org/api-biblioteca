package br.com.biblioteca.apibiblioteca.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"name", "age", "fone"})
@Entity
public class User_app implements Serializable {
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    private int age;

    private String fone;

    @JsonIgnore
    @OneToMany(mappedBy = "user_app")
    private List<Loan> loans = new ArrayList<>();

    public User_app () {
    }

}
