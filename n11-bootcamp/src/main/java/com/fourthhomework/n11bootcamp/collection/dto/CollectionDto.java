package com.fourthhomework.n11bootcamp.collection.dto;


import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class CollectionDto {

    private Long id;

    private Double collectionAmount;

    private Long debtId;

    private Long userId;

    @Temporal(TemporalType.DATE)
    private Date createdAt;

}
