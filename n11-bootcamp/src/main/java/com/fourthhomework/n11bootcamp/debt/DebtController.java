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

import static com.fourthhomework.n11bootcamp.constant.DebtTypeConstants.LATE_FEE;
import static com.fourthhomework.n11bootcamp.constant.DebtTypeConstants.NORMAL;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/debts")
public class DebtController {

    private final DebtService debtService;

    private final CreateDebtMapper debtMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createDebt(@RequestBody CreateDebtDto debtDto){
        Debt debt = debtMapper.toEntity(debtDto);
        debtService.createDebt(debt);
    }

    @GetMapping
    public ResponseEntity<?> retrieveDebtsByCreatedDate(@RequestParam Date startedDate, @RequestParam Date endDate){
        return ResponseEntity.ok(debtMapper.toDto(debtService.retrieveDebtsByCreatedDate(startedDate,endDate)));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> retrieveDebtsByUser(@PathVariable Long userId){
        return ResponseEntity.ok(debtMapper.toDto(debtService.retrieveDebtsByUser(userId)));
    }

    @GetMapping("/user/overdue/{userId}")
    public ResponseEntity<?> retrieveDebtsByOverDueAndUser(@PathVariable Long userId ){
        return ResponseEntity.ok(debtMapper.toDto(debtService.findDebtsByOverDueAndUser(userId)));
    }

    @GetMapping("/user/debt-amount/{userId}")
    public ResponseEntity<?> getAllDebtAmountByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(debtService.getAllDebtAmountByUserId(userId));
    }

    @GetMapping("/user/late-fee-amount/{userId}")
    public ResponseEntity<?> getTotalLateFeeAmountByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(debtService.getTotalLateFeeAmountByUserId(userId,NORMAL));
    }

    @GetMapping("/over-due-amount/{userId}")
    public ResponseEntity<Double> getAmountOverDueDebt(@PathVariable Long userId){
        return ResponseEntity.ok(debtService.findDebtsByOverDueAndUser(userId).stream().mapToDouble(Debt::getMainDebt).sum());
    }



}
