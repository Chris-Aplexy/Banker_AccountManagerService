package com.chris.Banker_AccountManagerService.controller;

import com.chris.Banker_AccountManagerService.model.Account;
import com.chris.Banker_AccountManagerService.service.AccountServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountServiceImpl accountService;

    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity createAccount(@RequestBody Account account) throws URISyntaxException {
        return ResponseEntity.ok(accountService.createAccount(account));
    }

    @GetMapping("/{id}")
    public ResponseEntity viewAccount(@PathVariable long id){
        return ResponseEntity.ok(accountService.viewAccount(id));
    }

    @GetMapping
    public ResponseEntity viewAllAccounts(){
        return ResponseEntity.ok(accountService.viewAllAccounts());
    }

    @PutMapping
    public ResponseEntity editAccountInfo(@RequestParam long id, @RequestBody Account account){
        return ResponseEntity.ok(accountService.editAccInfo(id, account));
    }

    @DeleteMapping
    public ResponseEntity deleteAccount(@RequestParam long id){
        return ResponseEntity.ok(accountService.deleteAccount(id));
    }

    @PutMapping("/webT")
    public ResponseEntity updateAccountAmount(@RequestParam String accountNumber,@RequestParam long amount){
        return ResponseEntity.ok(accountService.updateAccountAmount(accountNumber, amount));
    }
}
