package com.yuriy.labs.springcloud.microservices.guest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BillFallbackService implements BillClient {

    @Override
    public String payBills(Integer tableId) {
        log.warn("Fallback method to handle bill payment");
        return "Check stack trace";
    }

    @Override
    public void triggerError() {
        log.warn("Fallback method is called to handle the exception.");
    }
}
