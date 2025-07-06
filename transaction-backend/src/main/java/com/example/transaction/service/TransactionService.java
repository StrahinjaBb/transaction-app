package com.example.transaction.service;

import com.example.transaction.enums.TransactionStatus;
import com.example.transaction.model.Transaction;
import com.example.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    public Transaction addTransaction(Transaction transaction) {
        if (transaction.getStatus() == null) {
            transaction.setStatus(generateRandomStatus());
        }
        return repository.save(transaction);
    }

    private TransactionStatus generateRandomStatus() {
        return null;
    }
}
