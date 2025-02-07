package com.teachmeskills.lesson_32_33.financial_app.model;

import java.time.LocalDateTime;
import java.util.Objects;

public final class Transaction {
    private int transactionId;
    private String accountNumber;
    private LocalDateTime transactionTime;
    private String transactionType;
    private double amount;

    public Transaction(int transactionId, String accountNumber, LocalDateTime transactionTime, String transactionType, double amount) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.transactionTime = transactionTime;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return transactionId == that.transactionId && Objects.equals(accountNumber, that.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, accountNumber);
    }
}