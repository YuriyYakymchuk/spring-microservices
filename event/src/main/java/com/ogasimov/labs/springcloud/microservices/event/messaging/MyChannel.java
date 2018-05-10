package com.ogasimov.labs.springcloud.microservices.event.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MyChannel {

    String EVENT = "event";

    @Input(EVENT)
    SubscribableChannel event();
}
