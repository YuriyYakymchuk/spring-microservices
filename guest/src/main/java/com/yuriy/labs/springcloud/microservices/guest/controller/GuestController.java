package com.yuriy.labs.springcloud.microservices.guest.controller;

import java.util.List;

import com.yuriy.labs.springcloud.microservices.guest.service.DinnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class GuestController {

    @Autowired
    private DinnerService dinnerService;

    @PostMapping(value = "/dinner", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer startDinner(@RequestBody List<Integer> menuItems) {
        return dinnerService.startDinner(menuItems);
    }

    @DeleteMapping("/dinner/{tableId}")
    public String finishDinner(@PathVariable Integer tableId) {
        return dinnerService.finishDinner(tableId);
    }

    @GetMapping("/dinner/error")
    public String testCircuitBreaker() {
        return dinnerService.testCircuitBreaker();
    }

}
