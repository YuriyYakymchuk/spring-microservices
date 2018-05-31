package com.yuriy.labs.springcloud.microservices.guest.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MySources {

    @Output
    MessageChannel table();

    @Output
    MessageChannel event();

    @Output
    MessageChannel order();

    @Output
    MessageChannel bill();
}
