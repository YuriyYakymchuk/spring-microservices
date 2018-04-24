package com.ogasimov.labs.springcloud.microservices.order.controller;

import java.util.Map;

import com.ogasimov.labs.springcloud.microservices.order.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/menu")
    public Map<Integer, String> getMenu() {
        return menuService.getMenuItems();
    }
}
