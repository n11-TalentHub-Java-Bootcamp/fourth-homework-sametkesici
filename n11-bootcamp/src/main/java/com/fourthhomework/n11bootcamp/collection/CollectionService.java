package com.fourthhomework.n11bootcamp.collection;


import com.fourthhomework.n11bootcamp.debt.Debt;
import com.fourthhomework.n11bootcamp.debt.DebtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static com.fourthhomework.n11bootcamp.constant.DebtTypeConstants.LATE_FEE;


@Service
@Transactional
@RequiredArgsConstructor
public class CollectionService {

    private final CollectionRepository collectionRepository;

    private final DebtService debtService;


    public Collection makeCollection(Collection collection){
        Date createdDate = new Date();

        Debt debtWithLateFee = new Debt();
        debtWithLateFee.setRemainingDebt(0.0);
        debtWithLateFee.setDebtType(LATE_FEE);
        debtWithLateFee.setDebt(collection.getDebt());
        debtWithLateFee.setCreatedAt(createdDate);

        debtService.createDebt(debtWithLateFee);


        collection.setCreatedDate(createdDate);
        return collectionRepository.save(collection);
    }

    public List<Collection> retrieveCollectionsByCreatedDate(Date startedDate, Date endDate) {
        return collectionRepository.findByCreatedAtBetween(startedDate,endDate);
    }


    public Double calculateLateFee(){
        return 1.0;
    }

}
