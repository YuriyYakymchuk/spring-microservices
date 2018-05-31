package com.yuriy.labs.springcloud.microservices.event;

import java.util.List;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yuriy.labs.springcloud.microservices.common.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping("/events/{startId}/{count}")
    @HystrixCommand
    public List<EventDto> getEvents(@PathVariable("startId")  Integer startId, @PathVariable("count") Integer count) {
        return eventService.getEvents(startId, count);
    }
}
