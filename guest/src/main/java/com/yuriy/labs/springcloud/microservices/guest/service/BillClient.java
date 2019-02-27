package com.yuriy.labs.springcloud.microservices.guest.service;

import feign.Headers;
import feign.Param;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "Bill", qualifier = "BillClient", fallback = BillClientFallbackService.class)
public interface BillClient {

    @DeleteMapping("/bill/{tableId}")
    String payBills(@RequestHeader("Authorization") String token, @PathVariable("tableId") Integer tableId);

    @GetMapping("/bill/error")
    String throwException(@RequestHeader("Authorization") String token);

}