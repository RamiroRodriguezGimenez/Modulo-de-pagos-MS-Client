package com.client.feignclient;

import com.client.dto.response.AccountResponse;


import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(value = "api-gateway")
public interface AccountFeignClient {

    @GetMapping("/account-service/api/account/getByClient/{id}")
    public List<AccountResponse> getByClient(@PathVariable long id, @RequestHeader("Authorization") String authorizationHeader);


}
