package com.example.transaction.repository;

import com.example.transaction.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CsvTransactionRepository implements TransactionRepository {
    private final String csvFilePath = "/resources/transactions.csv";

    @Override
    public List<Transaction> findAll() {
        return null;
    }

    @Override
    public Transaction save(Transaction transaction) {
        return null;
    }

    public String getCsvFilePath() {
        return csvFilePath;
    }
}
