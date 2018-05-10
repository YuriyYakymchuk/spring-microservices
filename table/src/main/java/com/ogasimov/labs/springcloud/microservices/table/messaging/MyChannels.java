package com.ogasimov.labs.springcloud.microservices.table.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MyChannels {

    String TABLE = "table";

    @Input(TABLE)
    SubscribableChannel table();
}
