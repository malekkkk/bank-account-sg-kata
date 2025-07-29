package malek.jerbi.bank.account.controllers;

import malek.jerbi.bank.account.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bank/account")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping("/{bankAccountId}/deposit")
    public String deposit(@PathVariable int bankAccountId, @RequestParam int amount) {
        return bankAccountService.deposit(bankAccountId, amount);
    }

    @PostMapping("/{bankAccountId}/withdraw")
    public String withdraw(@PathVariable int bankAccountId, @RequestParam int amount) {
        return bankAccountService.withdraw(bankAccountId, amount);
    }

    @GetMapping("/:bankAccountId/statement")
    public List<String> statement(@PathVariable int bankAccountId) {
        return bankAccountService.getStatements(bankAccountId);
    }
}
