package com.ebanx.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebanx.account.dto.EventRequest;
import com.ebanx.account.dto.EventResponse;
import com.ebanx.account.service.EventService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/event")
public class EventController {

  @Autowired
  private EventService eventService;

  @PostMapping
  public ResponseEntity<EventResponse> eventHandler(@RequestBody EventRequest eventRequest) {
      if(eventRequest.getType().equals("deposit")){
        return new ResponseEntity<EventResponse>(eventService.deposit(eventRequest), HttpStatusCode.valueOf(201));
      }
      return null;
  }
}
