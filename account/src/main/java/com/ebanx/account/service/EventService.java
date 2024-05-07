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
    System.out.println(event.getId());
    Event event2 = new Event();
    System.out.println(event2.getId());
    if(account == null){
      account = new Account(eventRequest.getDestination(), eventRequest.getAmount());
      accountRepository.save(account);
      eventRepository.save(event);
      EventResponse eventResponse = new EventResponse();
      eventResponse.setDestination(mapper.map(account, AccountDTO.class));
      return eventResponse;
    }
    return null;
  }
}
