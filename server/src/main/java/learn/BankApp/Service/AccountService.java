package learn.BankApp.Service;

import learn.BankApp.Models.Account;
import learn.BankApp.Models.Transaction;
import learn.BankApp.Repository.AccountRepository;
import learn.BankApp.Repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Account getAccount(int accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Transactional
    public Account deposit(int accountId, double amount) {
        Account account = getAccount(accountId);

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0.");
        }

        double addBalance = account.getBalance() + amount;
        account.setBalance(addBalance);

        Transaction transaction = new Transaction(accountId, "Deposit", amount);

        transactionRepository.save(transaction);

        return account;
    }

    @Transactional
    public Account withdraw(int accountId, double amount) {
        Account account = getAccount(accountId);

        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0.");
        }

        double subtractBalance = account.getBalance() - amount;
        account.setBalance(subtractBalance);

        Transaction transaction = new Transaction(accountId, "Withdraw", amount);

        transactionRepository.save(transaction);

        return account;
    }

    public List<Transaction> getTransactions(int accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

}