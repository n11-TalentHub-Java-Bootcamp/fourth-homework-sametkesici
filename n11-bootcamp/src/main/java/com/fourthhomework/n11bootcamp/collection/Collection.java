package com.fourthhomework.n11bootcamp.collection;

import com.fourthhomework.n11bootcamp.debt.Debt;
import com.fourthhomework.n11bootcamp.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "collections")
public class Collection {

    @Id
    @GeneratedValue(generator = "generator")
    private Long id;

    private Double collectionAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    private Debt debt;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Temporal(TemporalType.DATE)
    private Date createdAt;

}
