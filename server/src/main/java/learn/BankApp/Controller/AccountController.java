package learn.BankApp.Controller;

import learn.BankApp.Models.Account;
import learn.BankApp.Models.Transaction;
import learn.BankApp.Service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{accountId}")
    public Account getAccount(@PathVariable int accountId) {
        return accountService.getAccount(accountId);
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @PostMapping("/{accountId}/deposit/{deposit}")
    public Account deposit(@PathVariable int accountId, @PathVariable BigDecimal deposit) {
        return accountService.deposit(accountId, deposit);
    }

    @PostMapping("/{accountId}/withdraw/{withdraw}")
    public Account withdraw(@PathVariable int accountId, @PathVariable BigDecimal withdraw) {
        return accountService.withdraw(accountId, withdraw);
    }

    @GetMapping("/{id}/transactions")
    public List<Transaction> getTransactions(@PathVariable int id) {
        return accountService.getTransactions(id);
    }
}
