package com.fourthhomework.n11bootcamp.collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {

    List<Collection> findByCreatedAtBetween(Date startDate , Date endDate);

    List<Collection> findByUserId(Long userId);

}
