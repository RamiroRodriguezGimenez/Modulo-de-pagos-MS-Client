package com.client.dto.response;

import com.commons.clients.models.entity.Client;

import java.util.List;

public class ClientResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private boolean isActive;

    public boolean isActive() {
        return isActive;
    }
    private List<AccountResponse> accounts;

    public List<AccountResponse> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountResponse> accounts) {
        this.accounts = accounts;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public ClientResponse(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.isActive = client.isActive();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
