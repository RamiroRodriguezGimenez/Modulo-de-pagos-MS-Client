package com.client.dto.response;

import org.springframework.http.ResponseEntity;

public class AccountResponse  {

    private Long id;

    private String number;

    private Double balance;

    private boolean active;

    public Long getId() {
        return id;
    }

    public AccountResponse() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
