package com.yuriy.labs.springcloud.microservices.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("Bill")
public interface BillClient {

    @PostMapping("/bill/{tableId}/{orderId}")
    void createBill(@PathVariable("tableId") Integer tableId, @PathVariable("orderId") Integer orderId);

    @DeleteMapping("/bills/{orderId}")
    void payBills(@PathVariable("orderId") Integer orderId);
}
