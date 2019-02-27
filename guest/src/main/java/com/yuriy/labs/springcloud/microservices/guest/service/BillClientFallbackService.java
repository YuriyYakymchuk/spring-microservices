package com.yuriy.labs.springcloud.microservices.guest.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("BillCLientFallback")
public class BillClientFallbackService implements BillClient{

    @Override
    public String payBills(String token, Integer tableId) {
        return "Pay bills fallback method executed.";
    }

    @Override
    public String throwException(String token) {
        return "Circuit Breaker works.";
    }
}
