package com.example.transaction.model;

import com.example.transaction.enums.TransactionStatus;

import java.time.LocalDate;

public class Transaction {
    private LocalDate date;
    private String accountNumber;
    private String accountHolder;
    private Double amount;
    private TransactionStatus status;

    public static Builder builder() {
        return new Builder();
    }

    //  Builder is added to demonstrate at least one design pattern - used in tests - not needed necessarily
    public static class Builder {
        private final Transaction transaction = new Transaction();

        public Builder date(LocalDate date) {
            transaction.setDate(date);
            return this;
        }

        public Builder accountNumber(String accountNumber) {
            transaction.setAccountNumber(accountNumber);
            return this;
        }

        public Builder accountHolder(String accountHolder) {
            transaction.setAccountHolder(accountHolder);
            return this;
        }

        public Builder amount(Double amount) {
            transaction.setAmount(amount);
            return this;
        }

        public Builder status(TransactionStatus status) {
            transaction.setStatus(status);
            return this;
        }

        public Transaction build() {
            return transaction;
        }
    }

    public Transaction() {

    }

    public Transaction(LocalDate date, String accountNumber, String accountHolder, double amount, TransactionStatus status) {
        this.date = date;
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.amount = amount;
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return date + "," +
                accountNumber + "," +
                accountHolder + "," +
                amount + "," +
                status;

    }
}
