package com.chris.Banker_AccountManagerService.service;

import com.chris.Banker_AccountManagerService.model.Account;
import com.chris.Banker_AccountManagerService.repository.AccountRepository;
import com.chris.Banker_AccountManagerService.service.serviceImpl.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;


    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> viewAccount(long id) {
        return accountRepository.findById(id);
    }

    @Override
    public List<Account> viewAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account editAccInfo(long id, Account account) {
       Optional<Account> oldAccount = accountRepository.findById(id);

       if(account.getAccountBalance() != 0) oldAccount.get().setAccountBalance(account.getAccountBalance());
       if(account.getAccountHolderName() != null) oldAccount.get().setAccountHolderName(account.getAccountHolderName());
        return accountRepository.save(oldAccount.get());
    }

    @Override
    public String deleteAccount(long id) {
        String delId = accountRepository.findById(id).get().getAccountNumber();
        accountRepository.deleteById(id);
        return "Bank account " + delId+ " deleted successfully";
    }
}
