package com.chris.Banker_AccountManagerService.service;

import com.chris.Banker_AccountManagerService.dto.Report;
import com.chris.Banker_AccountManagerService.model.Account;
import com.chris.Banker_AccountManagerService.repository.AccountRepository;
import com.chris.Banker_AccountManagerService.service.serviceImpl.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private WebClient client;
    private String baseUrl = "http://localhost:9000";

    public AccountServiceImpl(AccountRepository accountRepository, WebClient client) {
        this.accountRepository = accountRepository;
        this.client = client;
    }

    @Override
    public Account createAccount(Account account) throws URISyntaxException {

        Report accountCreationReport = new Report(
                "Account created with account number " +account.getAccountNumber() +" On " +new Date()
                        + " /n Enjoy the pleasant experience with Banker services",
                new Date()
        );

        if(accountRepository.save(account) != null) {
            this.createReportAccountCreation(accountCreationReport);
        }

        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> viewAccount(long id) {
        return accountRepository.findById(id);
    }

    @Override
    public List<Account> viewAllAccounts() {
       /* Report report = new Report();

        this.createReportAccountCreation(report);  //testing whether the report is generated in the report class
        this.client.get()
               // .uri("/")
                .retrieve()
                .bodyToFlux(Report.class)
                .collectList();
*/
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

    @Override
    public Account updateAccountAmount(String accountNumber, long amount) {
        Optional<Account> oldAccount = accountRepository.findByAccountNumber(accountNumber);

        if(amount != 0) oldAccount.get().setAccountBalance(amount);

        return accountRepository.save(oldAccount.get());
    }


    public Report createReportAccountCreation(Report report){
        String url = baseUrl + "/reports";

        try{
           Report reportMono = this.client.post()
                    .uri(url)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(report)
                    .retrieve().bodyToMono(Report.class).block();

           log.info(" Created a report "+ reportMono.toString());

            return reportMono;
        }catch(Exception e){
            log.info(String.valueOf(e));
        }
        return null;
    }
}
