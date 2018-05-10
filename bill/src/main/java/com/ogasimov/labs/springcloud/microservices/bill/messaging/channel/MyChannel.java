package com.ogasimov.labs.springcloud.microservices.bill.messaging.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MyChannel {

    String BILL = "bill";

    @Input(BILL)
    SubscribableChannel bill();
}
