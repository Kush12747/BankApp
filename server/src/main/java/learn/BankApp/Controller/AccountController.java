package learn.BankApp.Controller;

import learn.BankApp.Models.Account;
import learn.BankApp.Models.Transaction;
import learn.BankApp.Service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountId) {
        Account account = accountService.getAccount(accountId);
        return ResponseEntity.ok(account);
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createAccount = accountService.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(createAccount);
    }

    @PostMapping("/{accountId}/deposit/{amount}")
    public ResponseEntity<Account> deposit(@PathVariable String accountId, @PathVariable double amount) {
        Account updatedAccount = accountService.deposit(accountId, amount);
        return ResponseEntity.ok(updatedAccount);
    }

    @PostMapping("/{accountId}/withdraw/{amount}")
    public ResponseEntity<Account> withdraw(@PathVariable String accountId, @PathVariable double amount) {
        Account updatedAccount = accountService.withdraw(accountId, amount);
        return ResponseEntity.ok(updatedAccount);
    }

    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable String accountId) {
        List<Transaction> transactionList = accountService.getTransactions(accountId);

        return ResponseEntity.ok(transactionList);
    }
}