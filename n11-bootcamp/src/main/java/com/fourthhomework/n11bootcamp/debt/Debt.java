package com.fourthhomework.n11bootcamp.debt;

import com.fourthhomework.n11bootcamp.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Debt {

    @Id
    @GeneratedValue(generator = "generator")
    private Long id;

    @Column(updatable = false)
    private Double mainDebt;

    private Double remainingDebt;

    @Temporal(TemporalType.DATE)
    private Date dueDate;

    private String debtType;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Debt debt;

    @Temporal(TemporalType.DATE)
    private Date createdAt;

}
