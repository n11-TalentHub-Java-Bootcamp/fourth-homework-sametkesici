package com.fourthhomework.n11bootcamp.collection;

import com.fourthhomework.n11bootcamp.debt.Debt;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Setter
@Table(name = "collections")
public class Collection {

    @Id
    @GeneratedValue(generator = "generator")
    private Long id;

    private Double collectionAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    private Debt debt;

    @Temporal(value = TemporalType.DATE)
    private Date createdDate;

}
