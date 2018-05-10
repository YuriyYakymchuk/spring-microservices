package com.ogasimov.labs.springcloud.microservices.stock.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MyChannels {

    String STOCK = "stock";

    @Input(STOCK)
    SubscribableChannel stock();
}
