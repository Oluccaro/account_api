package com.ebanx.account.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebanx.account.dto.AccountDTO;
import com.ebanx.account.repository.AccountRepository;

@Service
public class AccountService {
  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private ModelMapper mappper;

  public AccountDTO getAccount(Long accountId){
    return mappper.map(accountRepository.findById(accountId), AccountDTO.class);
  }  
}
