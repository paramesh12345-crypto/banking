package com.example.banking.service;

import com.example.banking.dto.TransactionRequest;
import com.example.banking.entity.Account;
import com.example.banking.repo.AccountRepository;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("bank.transactionService")
@Log4j2
public class TransactionService {

    @Resource(name = "bank.accountRepository")
    private AccountRepository accountRepository;

    public boolean processTransaction(TransactionRequest transactionRequest) {
        Optional<Account> account = accountRepository.findByAccountNumber(transactionRequest.getAccountNumber());
        if (!account.isPresent()) {
            accountRepository.save(Account.builder().accountNumber(transactionRequest.getAccountNumber())
                    .balance(transactionRequest.getBalance())
                    .currency(transactionRequest.getCurrency())
                    .build());
        } else {
            log.warn("Account already exists with account number {}", transactionRequest.getAccountNumber());
        }
        return true;
    }
}
