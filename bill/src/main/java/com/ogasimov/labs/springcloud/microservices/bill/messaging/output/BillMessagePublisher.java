package com.ogasimov.labs.springcloud.microservices.bill.messaging.output;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class BillMessagePublisher {

    private final MessageChannel output;

    @Autowired
    public BillMessagePublisher(@Qualifier("bill") MessageChannel output) {
        this.output = output;
    }

    public void sendBillMessage(String message) {
        output.send(MessageBuilder.withPayload(message).build());
    }
}
