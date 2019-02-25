package com.yuriy.labs.springcloud.microservices.guest.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "Bill", qualifier = "Bill", fallback = BillFallbackService.class)
public interface BillClient {

    @DeleteMapping("/bills/{tableId}")
    void payBills(@PathVariable("tableId") Integer tableId);

}