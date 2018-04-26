package com.ogasimov.labs.springcloud.microservices.guest.service;

import org.springframework.stereotype.Component;

@Component
public class BillFallbackService implements BillClient {

    @Override
    public void payBills(Integer tableId) {
        System.out.println("Fallback method to handle bill payment");
    }

}
