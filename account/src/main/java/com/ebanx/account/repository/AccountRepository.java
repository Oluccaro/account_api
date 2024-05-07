package com.ebanx.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebanx.account.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
  
}
