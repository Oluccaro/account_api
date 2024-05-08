package com.ebanx.account.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebanx.account.dto.AccountDTO;
import com.ebanx.account.model.Account;
import com.ebanx.account.repository.AccountRepository;

@Service
public class AccountService {
  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private ModelMapper mapper;

  public AccountDTO getAccount(String accountId){
    return mapper.map(accountRepository.findById(accountId), AccountDTO.class);
  }  

  public AccountDTO createAccount(String accountId){
    Account account = new Account(accountId, (double)0);
    accountRepository.save(account);
    return mapper.map(account, AccountDTO.class);
  }

}
