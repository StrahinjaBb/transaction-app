package com.example.transaction.service;

import com.example.transaction.enums.TransactionStatus;
import com.example.transaction.model.Transaction;
import com.example.transaction.repository.TransactionRepository;
import com.example.transaction.util.TransactionValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {
    @Mock
    private TransactionRepository repository;

    @InjectMocks
    private TransactionService transactionService;

    private Transaction validTransaction;

    @BeforeEach
    void setUp() {
        validTransaction = Transaction.builder()
                .date(LocalDate.now())
                .accountNumber("1234-5678-9012")
                .accountHolder("John Doe")
                .amount(100.0)
                .build();
    }

    @Test
    void getAllTransactions_ShouldReturnAllTransactions() {
        List<Transaction> expectedTransactions = Arrays.asList(
                new Transaction(),
                new Transaction()
        );
        when(repository.findAll()).thenReturn(expectedTransactions);

        List<Transaction> actualTransactions = transactionService.getAllTransactions();

        assertEquals(expectedTransactions.size(), actualTransactions.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void addTransaction_WithValidTransaction_ShouldSaveAndReturnTransaction() {
        when(repository.save(any(Transaction.class))).thenReturn(validTransaction);

        Transaction result = transactionService.addTransaction(validTransaction);

        assertNotNull(result);
        assertNotNull(result.getStatus());
        verify(repository, times(1)).save(validTransaction);
    }

    @Test
    void addTransaction_WithNullStatus_ShouldGenerateRandomStatus() {
        validTransaction.setStatus(null);
        when(repository.save(any(Transaction.class))).thenReturn(validTransaction);

        Transaction result = transactionService.addTransaction(validTransaction);

        assertNotNull(result.getStatus());
        verify(repository, times(1)).save(validTransaction);
    }

    @Test
    void addTransaction_WithExistingStatus_ShouldNotChangeStatus() {
        validTransaction.setStatus(TransactionStatus.PENDING);
        when(repository.save(any(Transaction.class))).thenReturn(validTransaction);

        Transaction result = transactionService.addTransaction(validTransaction);

        assertEquals(TransactionStatus.PENDING, result.getStatus());
        verify(repository, times(1)).save(validTransaction);
    }

    @Test
    void addTransaction_WithInvalidTransaction_ShouldThrowException() {
        Transaction invalidTransaction = Transaction.builder()
                .date(null)
                .accountNumber("invalid")
                .accountHolder("123Invalid")
                .amount(-10.0)
                .build();

        try (MockedStatic<TransactionValidator> validator = mockStatic(TransactionValidator.class)) {
            validator.when(() -> TransactionValidator.validateTransaction(invalidTransaction))
                    .thenThrow(new IllegalArgumentException("Invalid transaction"));

            assertThrows(IllegalArgumentException.class, () -> {
                transactionService.addTransaction(invalidTransaction);
            });

            verify(repository, never()).save(any());
        }
    }

    @Test
    void generateRandomStatus_ShouldReturnValidStatus() {
        TransactionStatus status = transactionService.generateRandomStatus();

        assertNotNull(status);
        assertTrue(Arrays.asList(TransactionStatus.values()).contains(status));
    }
}