package learn.BankApp.Controller;

import learn.BankApp.Models.Account;
import learn.BankApp.Models.Transaction;
import learn.BankApp.Service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{accountId}")
    public Account getAccount(@PathVariable String accountId) {
        return accountService.getAccount(accountId);
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @PostMapping("/{accountId}/deposit/{amount}")
    public Account deposit(@PathVariable String accountId,
                           @PathVariable double amount) {

        return accountService.deposit(accountId, amount);
    }

    @PostMapping("/{accountId}/withdraw/{amount}")
    public Account withdraw(@PathVariable String accountId,
                            @PathVariable double amount) {

        return accountService.withdraw(accountId, amount);
    }

    @GetMapping("/{accountId}/transactions")
    public List<Transaction> getTransactions(
            @PathVariable String accountId) {

        return accountService.getTransactions(accountId);
    }
}