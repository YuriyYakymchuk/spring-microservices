package com.yuriy.labs.springcloud.microservices.guest.service;

import com.yuriy.labs.springcloud.microservices.common.EventDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("Event")
public interface EventClient {

    @GetMapping("/events/{startId}/{count}")
    List<EventDto> getEvents(@PathVariable("startId") Integer startId, @PathVariable("count") Integer count );
}
