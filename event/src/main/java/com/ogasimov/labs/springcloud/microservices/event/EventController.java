package com.ogasimov.labs.springcloud.microservices.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping("/events/{startId}/{count}")
    public List<Event> getEvents(@PathVariable("startId")  Integer startId, @PathVariable("endId") Integer endId) {
        return eventService.getBetween(startId, endId);
    }
}
