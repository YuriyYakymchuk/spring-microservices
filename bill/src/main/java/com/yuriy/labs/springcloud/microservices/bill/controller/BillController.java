package com.yuriy.labs.springcloud.microservices.bill.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yuriy.labs.springcloud.microservices.bill.model.Bill;
import com.yuriy.labs.springcloud.microservices.bill.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping("/bill")
    @HystrixCommand
    public List<Bill> getAllBills() {
        return billService.getAllBills();
    }

    @PostMapping("/bill/{tableId}/{orderId}")
    @HystrixCommand
    public void createBill(@PathVariable Integer tableId, @PathVariable Integer orderId) {
        billService.createBill(tableId, orderId);
    }

    @HystrixCommand(fallbackMethod = "successFallbackMethod")
    @DeleteMapping("/bills/{tableId}")
    public String payBills(@PathVariable Integer tableId) {
        billService.payBills(tableId);
        return "Ok";
    }

    @HystrixCommand
    @GetMapping("/bill/message")
    public String getMessage() {
        return billService.getMessage();
    }

    public String successFallbackMethod(Integer tableId) {
        return "Something went wrong.";
    }
}
