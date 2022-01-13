package com.fourthhomework.n11bootcamp.debt;


import com.fourthhomework.n11bootcamp.user.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "debts")
public class Debt {

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(updatable = false)
    private Double mainDebt;

    private Double remainingDebt;

    @Temporal(TemporalType.DATE)
    private Date dueDate;

    private String debtType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Debt debt;

    @Temporal(TemporalType.DATE)
    private Date createdAt;

}
