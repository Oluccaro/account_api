package com.ebanx.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ebanx.account.dto.AccountDTO;
import com.ebanx.account.dto.EventRequest;
import com.ebanx.account.dto.EventResponse;
import com.ebanx.account.service.AccountService;
import com.ebanx.account.service.EventService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class EventController {

  @Autowired
  private EventService eventService;

  @Autowired
  private AccountService accountService;

  @PostMapping("/event")
  public ResponseEntity<Object> eventHandler(@RequestBody EventRequest eventRequest) {
    
    switch(eventRequest.getType()){
      case "deposit":
        return deposit(eventRequest);
      case "withdraw":
        return withdraw(eventRequest);
      case "transfer":
        return transfer(eventRequest);
      default:
        return new ResponseEntity<Object>(HttpStatusCode.valueOf(400));
    }
  }

  @PostMapping("/reset")
  public ResponseEntity<String> postMethodName() {
    eventService.reset();
    return new ResponseEntity<String>("OK", HttpStatusCode.valueOf(200));
  }
  

  private ResponseEntity<Object> deposit(EventRequest eventRequest){
    AccountDTO destAccountDTO = accountService.getAccount(eventRequest.getDestination());
    if(destAccountDTO == null){
      destAccountDTO = accountService.createAccount(eventRequest.getDestination());
    }
    return new ResponseEntity<Object>(eventService.deposit(eventRequest), HttpStatusCode.valueOf(201));
  }

  private ResponseEntity<Object> withdraw(EventRequest eventRequest){
    AccountDTO originAccountDTO = accountService.getAccount(eventRequest.getOrigin());
    if(originAccountDTO == null){
      return new ResponseEntity<Object>((double) 0, HttpStatusCode.valueOf(404));
    } 
    return new ResponseEntity<Object>(eventService.withdraw(eventRequest), HttpStatusCode.valueOf(201));
  }

  private ResponseEntity<Object> transfer(EventRequest eventRequest){
    AccountDTO destAccountDTO = accountService.getAccount(eventRequest.getDestination());
    AccountDTO originAccountDTO = accountService.getAccount(eventRequest.getOrigin());
    if(originAccountDTO == null){
      return new ResponseEntity<Object>((double) 0, HttpStatusCode.valueOf(404));
    }
    if(destAccountDTO == null ){
      destAccountDTO = accountService.createAccount(eventRequest.getDestination());
    }
    return new ResponseEntity<Object>(eventService.transfer(eventRequest), HttpStatusCode.valueOf(201));
  }

}
