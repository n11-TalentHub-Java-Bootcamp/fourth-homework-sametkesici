package com.fourthhomework.n11bootcamp.user;

import com.fourthhomework.n11bootcamp.debt.Debt;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(updatable = false)
    private UUID id;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String lastName;

    @OneToMany(cascade = CascadeType.REMOVE , mappedBy = "user")
    private List<Debt> debts;

}
