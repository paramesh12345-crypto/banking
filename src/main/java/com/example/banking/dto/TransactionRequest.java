package com.example.banking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionRequest {

    @NotBlank(message = "Account number is mandatory")
    @Size(min = 10, max = 10, message = "Account Number must be exactly 10 numbers long")
    private String accountNumber;

    @NotNull(message = "Amount is mandatory")
    @Positive(message = "Amount must be greater than zero")
    private Double balance;

    private String currency;
}
