package com.client.controller;

import com.client.dto.request.RequestClientRegister;
import com.client.dto.response.ClientResponse;
import com.client.service.ClientService;

import com.commons.clients.models.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    ClientService clientService;



    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RequestClientRegister requestClientRegister){
        return clientService.register(requestClientRegister.getFirstName(), requestClientRegister.getLastName(), requestClientRegister.getEmail(), requestClientRegister.getPassword());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id, @RequestHeader("Authorization") String accessToken){



        ClientResponse client=clientService.getById(id, accessToken);
        if (client==null) {
            return new ResponseEntity<Object>("Client not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll( ){


        List<ClientResponse> clients=clientService.getAll();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<Object> edit(@RequestBody RequestClientRegister client){
        return clientService.edit(client);
    }
    @PutMapping("/cancel/{id}")
    public ResponseEntity<Object> cancel(@PathVariable Long id){
        return clientService.cancel(id);
    }

    @GetMapping("/getAccounts/{clientId}")
    public ResponseEntity<Object> getAccounts(@PathVariable long clientId, @RequestHeader("Authorization") String accessToken){

        return clientService.getAccounts(clientId, accessToken);
    }
    @GetMapping("/getByEmail")
    public Client getByEmail(@RequestParam String email){
        return clientService.getByEmail(email);
    }
}
