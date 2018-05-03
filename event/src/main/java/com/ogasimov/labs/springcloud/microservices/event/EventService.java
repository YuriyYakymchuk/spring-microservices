package com.ogasimov.labs.springcloud.microservices.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EventService {

    private final EventRepository eventRepository;

    private final ObjectMapper objectMapper;

    @Autowired
    public EventService(EventRepository eventRepository, ObjectMapper objectMapper) {
        this.eventRepository = eventRepository;
        this.objectMapper = objectMapper;
    }

    public List<Event> getBetween(Integer startID, Integer endID) {
        return eventRepository.findAllByIdBetween(startID, endID);
    }

    public void persistEvent(Object payload) throws Exception {
        Event event = new Event();
        event.setPayload(objectMapper.writeValueAsString(payload));
        event.setType(payload.getClass().getName());
        eventRepository.save(event);
    }
}
