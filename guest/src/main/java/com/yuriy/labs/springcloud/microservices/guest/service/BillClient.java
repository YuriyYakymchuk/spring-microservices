package com.yuriy.labs.springcloud.microservices.guest.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "Bill", fallback = BillFallbackService.class)
public interface BillClient {

    @DeleteMapping("/bill/{tableId}")
    String payBills(@PathVariable("tableId") Integer tableId);

    @GetMapping("/bill/error")
    void triggerError();

}