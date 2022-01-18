package com.fourthhomework.n11bootcamp.user;

import com.fourthhomework.n11bootcamp.collection.Collection;
import com.fourthhomework.n11bootcamp.debt.Debt;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "generator")
    @Column(nullable = false,updatable = false)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String lastName;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.REMOVE)
    private List<Debt> debts;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.REMOVE)
    private List<Collection> collections;

}
