package com.ogasimov.labs.springcloud.microservices.bill;

import com.ogasimov.labs.springcloud.microservices.bill.messaging.channel.Channel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableBinding(Channel.class)
public class BillApp {
    public static void main(String[] args) {
        SpringApplication.run(BillApp.class, args);
    }
}
