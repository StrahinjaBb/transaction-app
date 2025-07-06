package com.example.transaction.model;

import com.example.transaction.enums.TransactionStatus;

import java.time.LocalDate;

public class Transaction {
    private LocalDate date;
    private String accountNumber;
    private String accountHolder;
    private double amount;
    private TransactionStatus status;

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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
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
