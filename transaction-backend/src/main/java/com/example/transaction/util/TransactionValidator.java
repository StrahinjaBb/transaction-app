package com.example.transaction.util;

import com.example.transaction.model.Transaction;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class TransactionValidator {
    private static final Pattern ACCOUNT_NUMBER_PATTERN = Pattern.compile("^\\d{4}-\\d{4}-\\d{4}$");

    public static void validateTransaction(Transaction transaction) throws IllegalArgumentException {
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction cannot be null");
        }

        try {
            LocalDate.parse(transaction.getDate().toString());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date");
        }

        if (transaction.getAccountNumber() == null ||
                !ACCOUNT_NUMBER_PATTERN.matcher(transaction.getAccountNumber()).matches()) {
            throw new IllegalArgumentException("Account number must be in format xxxx-xxxx-xxxx, where x is a number");
        }

        if (transaction.getAccountHolder() == null ||
                transaction.getAccountHolder().trim().isEmpty()) {
            throw new IllegalArgumentException("Account holder cannot be empty");
        }

        if (transaction.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }
}