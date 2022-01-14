package com.fourthhomework.n11bootcamp.collection;

import com.fourthhomework.n11bootcamp.collection.dto.CreateCollectionDto;
import com.fourthhomework.n11bootcamp.mapper.CreateCollectionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.Date;

@RestController
@RequiredArgsConstructor
@Transactional
@RequestMapping("/collection")
public class CollectionController {

    private final CreateCollectionMapper createCollectionMapper;

    private final CollectionService collectionService;

    @PostMapping
    public ResponseEntity<?> makeCollection(@RequestBody CreateCollectionDto createCollectionDto){
        return ResponseEntity.ok(collectionService.makeCollection(createCollectionMapper.toEntity(createCollectionDto)));
    }

    @GetMapping
    private ResponseEntity<?> retrieveDebtsByCreatedDate(@RequestParam Date startedDate, @RequestParam Date endDate){
        return ResponseEntity.ok(createCollectionMapper.toDto(collectionService.retrieveCollectionsByCreatedDate(startedDate,endDate)));
    }




}
