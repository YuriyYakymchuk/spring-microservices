package com.ogasimov.labs.springcloud.microservices.bill.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ogasimov.labs.springcloud.microservices.bill.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping("/bill/{tableId}/{orderId}")
    public void createBill(@PathVariable Integer tableId, @PathVariable Integer orderId) {
        billService.createBill(tableId, orderId);
    }

    @HystrixCommand(fallbackMethod = "fallbackMethod")
    @DeleteMapping("/bills/{tableId}")
    public String payBills(@PathVariable Integer tableId) {
        billService.payBills(tableId);
        return "Ok";
    }

    @GetMapping("/bill/message")
    public String getMessage() {
        return billService.getMessage();
    }

    public String fallbackMethod(Integer tableId) {
        throw new RuntimeException("Something went wrong");
    }
}
