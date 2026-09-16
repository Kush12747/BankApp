package learn.BankApp.Models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transactions")
public class Transaction {

    @Id
    private String txnId;

    private String accountId;
    private String transactionType;
    private double amount;
    private LocalDateTime createdAt;

    public Transaction(String accountId, String transactionType, double amount) {
        this.accountId = accountId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
    }

    public Transaction() {
    }

    public String getTransactionId() {
        return txnId;
    }

    public void setTransactionId(String txnId) {
        this.txnId = txnId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
