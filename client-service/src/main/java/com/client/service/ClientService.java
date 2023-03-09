package com.client.service;


import com.client.dto.request.RequestClientRegister;
import com.client.dto.response.AccountResponse;
import com.client.dto.response.ClientResponse;
import com.client.exception.NotFoundException;
import com.client.feignclient.AccountFeignClient;
import com.client.repository.ClientRepository;
import com.commons.clients.models.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    AccountFeignClient accountFeignClient;


    @Bean
    public static BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public ResponseEntity<Object> register(String firstName,  String lastName,String email,String password){
       ResponseEntity<Object> response = validate(firstName, lastName, email, passwordEncoder().encode(password));

        if (response.getStatusCodeValue()==201) {
            Client client = new Client(firstName, lastName, email, passwordEncoder().encode(password));

            clientRepository.save(client);
        }

        return response;
    }
    public Client getByEmail(String email){
        Client client= clientRepository.findByEmail(email);

        return client;
    }
    public ResponseEntity<Object> validate(String firstName,  String lastName,String email,String password){
      /*  if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.BAD_REQUEST);
        }
        Client client = clientRepository.findByEmail(email);
        if (client!= null){
            return new ResponseEntity<>("Email already used", HttpStatus.BAD_REQUEST);
        }*/
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ClientResponse getById(Long id, String accessToken) {
    ClientResponse clientResponse=clientRepository.findById(id).map(ClientResponse::new).orElse(null);
        if (clientResponse == null) {
            throw new NotFoundException("client not found");
        }
    clientResponse.setAccounts((List<AccountResponse>) (getAccounts(clientResponse.getId(), accessToken)).getBody());
            return clientResponse;

    }

    public List<ClientResponse> getAll(){

        return clientRepository.findAll().stream().map(client -> new ClientResponse(client)).collect(Collectors.toList());
    }

    public ResponseEntity<Object> cancel(Long id){
        Client client= clientRepository.findById(id).orElse(null);
        if (client == null) {
            throw new NotFoundException("client not found");
        }
        client.setActive(!client.isActive());
        clientRepository.save(client);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Object> edit(RequestClientRegister newClient){
        Client oldClient = clientRepository.findById(newClient.getId()).orElse(null);
        if (oldClient == null) {
            throw new NotFoundException("client not found");
           // return new ResponseEntity<Object>("Client not found", HttpStatus.NOT_FOUND);
        }

        clientRepository.save(oldClient);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    public  ResponseEntity<Object> getAccounts(Long id, String accessToken){
        List<AccountResponse> accountResponses= accountFeignClient.getByClient(id, accessToken);
        return new ResponseEntity<>(accountResponses, HttpStatus.OK);
    }

    public List<AccountResponse> getBody(@RequestBody List<AccountResponse> accountResponses){
        return accountResponses;
    }


}
