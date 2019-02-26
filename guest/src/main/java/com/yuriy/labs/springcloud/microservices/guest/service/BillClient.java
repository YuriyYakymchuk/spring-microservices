package com.yuriy.labs.springcloud.microservices.guest.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "Bill", qualifier = "BillClient", fallback = BillClientFallbackService.class)
public interface BillClient {

    @DeleteMapping("/bill/{tableId}")
    String payBills(@PathVariable("tableId") Integer tableId);

    @GetMapping("/bill/error")
    String throwException() ;

}