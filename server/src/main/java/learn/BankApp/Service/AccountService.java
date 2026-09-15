package learn.BankApp.Service;

import learn.BankApp.Models.Account;
import learn.BankApp.Models.Transaction;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    private List<Account> accounts = new ArrayList<>();
    private final List<Transaction> transactions = new ArrayList<>();

    public AccountService() {
        accounts.add(new Account(1, 1, new BigDecimal(100), "Checking", LocalDateTime.now()));
    }

    public Account getAccount(int accountId) {
        return accounts.stream()
                .filter(account -> account.getAccountId() == accountId)
                .findFirst()
                .orElse(null);
    }

    public Account createAccount(Account account) {
        accounts.add(account);
        return account;
    }

    public Account deposit(int accountId, BigDecimal amount) {
        Account account = getAccount(accountId);

        if (account == null) {
            throw new IllegalArgumentException("No account detected");
        }

        account.setBalance(account.getBalance().add(amount));

        transactions.add(new Transaction(1, accountId, "Deposit", amount, LocalDateTime.now()));

        return account;
    }


    public Account withdraw(int accountId, BigDecimal amount) {
        Account account = getAccount(accountId);

        if (account == null) {
            throw new IllegalArgumentException("No account detected");
        }

        if (account.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        account.setBalance(account.getBalance().subtract(amount));

        transactions.add(new Transaction(2, accountId, "Withdraw", amount, LocalDateTime.now()));

        return account;
    }

    public List<Transaction> getTransactions(int accountId) {
        return transactions.stream()
                .filter(transaction -> transaction.getAccountId() == accountId)
                .toList();
    }

}