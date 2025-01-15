package com.example.banking.controller;

import com.example.banking.dto.TransactionRequest;
import com.example.banking.service.TransactionService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Resource(name = "bank.transactionService")
    private TransactionService transactionService;

    @GetMapping
    public String getStatus() {
        return "Bank application is up and running";
    }

    @PostMapping
    public ResponseEntity<String> processTransaction(@RequestHeader(value = "Authorization") String authHeader,
                                                     @Valid @RequestBody TransactionRequest transactionRequest) {
        if (!authHeader.contains("Venkata")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid header details");
        }

        boolean isProcessed = transactionService.processTransaction(transactionRequest);

        if (isProcessed) {
            return ResponseEntity.status(HttpStatus.OK).body("Transaction is successful and account created");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid transaction details");
        }
    }
}
