package com.yuriy.labs.springcloud.microservices.guest.controller;

import java.util.List;

import com.yuriy.labs.springcloud.microservices.guest.service.DinnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuestController {

    @Autowired
    private DinnerService dinnerService;

    @PostMapping(value = "/dinner", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer startDinner(@RequestBody List<Integer> menuItems) {
        return dinnerService.startDinner(menuItems);
    }

    @DeleteMapping("/dinner/{tableId}")
    public void finishDinner(@PathVariable Integer tableId) {
        dinnerService.finishDinner(tableId);
    }

}
