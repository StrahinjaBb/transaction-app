package com.example.transaction.service;

import com.example.transaction.enums.TransactionStatus;
import com.example.transaction.model.Transaction;
import com.example.transaction.repository.TransactionRepository;
import com.example.transaction.util.TransactionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class TransactionService {
    @Autowired
    private final TransactionRepository repository;
    private static final Random random = new Random();

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    public Transaction addTransaction(Transaction transaction) {
        TransactionValidator.validateTransaction(transaction);

        if (transaction.getStatus() == null) {
            transaction.setStatus(generateRandomStatus());
        }
        return repository.save(transaction);
    }

    private TransactionStatus generateRandomStatus() {
        TransactionStatus[] statuses = TransactionStatus.values();
        int randomIndex = random.nextInt(statuses.length);
        return statuses[randomIndex];
    }
}
