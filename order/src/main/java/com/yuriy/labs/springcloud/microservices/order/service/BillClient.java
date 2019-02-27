package com.yuriy.labs.springcloud.microservices.order.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("Bill")
public interface BillClient {

    @PostMapping("/bill/{tableId}/{orderId}")
    void createBill(@RequestHeader("Authorization") String token, @PathVariable("tableId") Integer tableId, @PathVariable("orderId") Integer orderId);

}
