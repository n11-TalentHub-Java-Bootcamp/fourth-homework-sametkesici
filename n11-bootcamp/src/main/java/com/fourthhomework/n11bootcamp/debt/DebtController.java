package com.fourthhomework.n11bootcamp.debt;

import com.fourthhomework.n11bootcamp.debt.dto.CreateDebtDto;
import com.fourthhomework.n11bootcamp.debt.dto.DebtDto;
import com.fourthhomework.n11bootcamp.mapper.CreateDebtMapper;
import com.fourthhomework.n11bootcamp.mapper.DebtMapper;
import lombok.RequiredArgsConstructor;
import java.sql.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/debts")
public class DebtController {

    private final DebtService debtService;

    private final CreateDebtMapper debtMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private void createDebt(@RequestBody CreateDebtDto debtDto){
        Debt debt = debtMapper.toEntity(debtDto);
        debtService.createDebt(debt);
    }

    @GetMapping
    private ResponseEntity<?> retrieveDebtsByCreatedDate(@RequestParam Date startedDate, @RequestParam Date endDate){
        return ResponseEntity.ok(debtMapper.toDto(debtService.retrieveDebtsByCreatedDate(startedDate,endDate)));
    }

    @GetMapping("/user/{userId}")
    private ResponseEntity<?> retrieveDebtsByUser(@PathVariable Long userId){
        return ResponseEntity.ok(debtMapper.toDto(debtService.retrieveDebtsByUser(userId)));
    }

    @GetMapping("/user/{userId}/duedate/")
    private ResponseEntity<?> retrieveDebtsByOverDueAndUser(@PathVariable Long userId , @RequestParam Date dueDate){
        return ResponseEntity.ok(debtMapper.toDto(debtService.findDebtsByOverDueAndUser(dueDate,userId)));
    }



}
