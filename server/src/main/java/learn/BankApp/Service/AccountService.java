package learn.BankApp.Service;

import learn.BankApp.Models.Account;
import learn.BankApp.Models.Transaction;
import learn.BankApp.Repository.AccountRepository;
import learn.BankApp.Repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Account getAccount(String accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account deposit(String accountId, double amount) {
        Account account = getAccount(accountId);

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0.");
        }

        double addBalance = account.getBalance() + amount;
        account.setBalance(addBalance);

        Transaction transaction = new Transaction(accountId, "Deposit", amount);

        accountRepository.save(account);
        transactionRepository.save(transaction);

        return account;
    }

    public Account withdraw(String accountId, double amount) {
        Account account = getAccount(accountId);

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0.");
        }

        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        double subtractBalance = account.getBalance() - amount;
        account.setBalance(subtractBalance);

        Transaction transaction = new Transaction(accountId, "Withdraw", amount);

        transactionRepository.save(transaction);

        return accountRepository.save(account);
    }

    public List<Transaction> getTransactions(String accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

}