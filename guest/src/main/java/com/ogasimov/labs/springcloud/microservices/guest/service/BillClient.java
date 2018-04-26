package com.ogasimov.labs.springcloud.microservices.guest.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "Bill", fallback = BillFallbackService.class)
public interface BillClient {

    @DeleteMapping("/bills/{tableId}")
    @HystrixCommand(fallbackMethod = "payBillsFallback")
    void payBills(@PathVariable("tableId") Integer tableId);

    default void payBillsFallback(Integer tableId) {
        System.out.println("Fallback method");
    }

}