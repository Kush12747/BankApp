package learn.BankApp.Models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private int txnId;
    private int accountId;
    private String transactionType;
    private BigDecimal amount;
    private LocalDateTime createdAt;

    public Transaction(int txnId, int accountId, String transactionType, BigDecimal amount, LocalDateTime createdAt) {
        this.txnId = txnId;
        this.accountId = accountId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public Transaction() {
    }

    public int getTransactionId() {
        return txnId;
    }

    public void setTransactionId(int txnId) {
        this.txnId = txnId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
