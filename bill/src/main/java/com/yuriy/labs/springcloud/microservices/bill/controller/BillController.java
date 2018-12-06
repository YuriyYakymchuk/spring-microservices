package com.yuriy.labs.springcloud.microservices.bill.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yuriy.labs.springcloud.microservices.bill.messaging.output.BillMessagePublisher;
import com.yuriy.labs.springcloud.microservices.bill.model.Bill;
import com.yuriy.labs.springcloud.microservices.bill.service.BillService;
import com.yuriy.labs.springcloud.microservices.bill.service.BillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private BillMessagePublisher billMessagePublisher;

    @GetMapping("/bill")
    @HystrixCommand
    public List<Bill> getAllBills() {
        return billService.getAllBills();
    }

    @PostMapping("/bill/message")
    public void createBillMessage(@RequestBody String message) {
        billMessagePublisher.sendBillMessage(message);
    }

    @PostMapping("/bill/{tableId}/{orderId}")
    @HystrixCommand
    public void createBill(@PathVariable Integer tableId, @PathVariable Integer orderId) {
        billService.createBill(tableId, orderId);
    }

    @HystrixCommand
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

    @PostMapping("/bill/git")
    public void gitWebHook() {
        System.out.println("Web hook!!!");
    }

    public String fallbackMethod(Integer tableId) {
        throw new RuntimeException("Something went wrong");
    }
}
