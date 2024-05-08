package com.ebanx.account.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebanx.account.dto.AccountDTO;
import com.ebanx.account.dto.EventRequest;
import com.ebanx.account.dto.EventResponse;
import com.ebanx.account.model.Account;
import com.ebanx.account.model.Event;
import com.ebanx.account.repository.AccountRepository;
import com.ebanx.account.repository.EventRepository;

@Service
public class EventService {
  
  @Autowired
  private EventRepository eventRepository;

  @Autowired 
  private AccountRepository accountRepository;

  @Autowired
  private ModelMapper mapper;

  public EventResponse deposit(EventRequest eventRequest){
    Account account = accountRepository.findById(eventRequest.getDestination()).orElse(null);
    Event event = mapper.map(eventRequest, Event.class);
    account.deposit(event.getAmount());
    accountRepository.save(account);
    eventRepository.save(event);
    EventResponse eventResponse = new EventResponse();
    eventResponse.setDestination(mapper.map(account, AccountDTO.class));
    return eventResponse;
  }

  public EventResponse withdraw(EventRequest eventRequest){
    Account account = accountRepository.findById(eventRequest.getOrigin()).orElse(null);
    Event event = mapper.map(eventRequest, Event.class);
    account.withdraw(event.getAmount());
    accountRepository.save(account);
    eventRepository.save(event);
    EventResponse eventResponse = new EventResponse();
    eventResponse.setOrigin(mapper.map(account, AccountDTO.class));
    return eventResponse;
  }

  public EventResponse transfer(EventRequest eventRequest){
    Account origin = accountRepository.findById(eventRequest.getOrigin()).orElse(null);
    Account destination = accountRepository.findById(eventRequest.getDestination()).orElse(null);
    Event event = mapper.map(eventRequest, Event.class);

    //transfer
    origin.withdraw(event.getAmount());
    destination.deposit(event.getAmount());

    //saving
    accountRepository.save(origin);
    accountRepository.save(destination);
    eventRepository.save(event);

    //response
    EventResponse eventResponse = new EventResponse();
    eventResponse.setOrigin(mapper.map(origin, AccountDTO.class));
    eventResponse.setDestination(mapper.map(destination, AccountDTO.class));
    return eventResponse;
  }

  public void reset(){
    eventRepository.deleteAll();
    accountRepository.deleteAll();
  }
}
