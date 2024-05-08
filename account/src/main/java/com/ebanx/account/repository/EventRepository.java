package com.ebanx.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebanx.account.model.Event;

public interface EventRepository extends JpaRepository<Event,Long> {
  
}
