package com.ebanx.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ebanx.account.dto.AccountDTO;
import com.ebanx.account.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class AccountController {
  @Autowired
  private AccountService accountService;

  @GetMapping("/balance")
  public ResponseEntity<Double> getBalance(@RequestParam("account_id") String accountId) {
      AccountDTO account =  accountService.getAccount(accountId);
      if (account == null) return new ResponseEntity<Double>((double) 0, HttpStatusCode.valueOf(404));
      return new ResponseEntity<Double>(account.getBalance(), HttpStatusCode.valueOf(200));
  }
}
