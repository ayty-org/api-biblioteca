package br.com.biblioteca.apibiblioteca.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserApp implements Serializable {
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    private int age;

    private String fone;

    @JsonIgnore
    @OneToMany(mappedBy = "userApp")
    private List<Loan> loans = new ArrayList<>();

    public UserApp(String name, int age, String fone) {
        this.name = name;
        this.age = age;
        this.fone = fone;
    }
    public UserApp(Long id, String name, int age, String fone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.fone = fone;
    }
}
