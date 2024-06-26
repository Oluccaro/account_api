package com.ebanx.account.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "account")
public class Account {
  @Id
  @Column(name="account_id")
  private String id;
  @Column(name="balance")
  private Double balance;

  public void deposit(Double value){
    this.balance += value;
  }

  public void withdraw(Double value){
    this.balance -= value;
  }
}
