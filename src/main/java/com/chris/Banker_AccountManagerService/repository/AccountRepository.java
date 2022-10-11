package com.chris.Banker_AccountManagerService.repository;

import com.chris.Banker_AccountManagerService.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
