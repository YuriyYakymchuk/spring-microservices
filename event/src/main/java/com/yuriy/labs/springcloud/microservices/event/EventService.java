package com.yuriy.labs.springcloud.microservices.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuriy.labs.springcloud.microservices.common.EventDto;
import com.yuriy.labs.springcloud.microservices.event.messaging.MyChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EventService {

    private static final Logger log = LoggerFactory.getLogger(EventService.class);

    private final EventRepository eventRepository;

    private final ObjectMapper objectMapper;

    @Autowired
    public EventService(EventRepository eventRepository, ObjectMapper objectMapper) {
        this.eventRepository = eventRepository;
        this.objectMapper = objectMapper;
    }

    @StreamListener(MyChannel.EVENT)
    private void streamListener(Object payload) {
        try {
            persistEvent(payload);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    public void persistEvent(Object payload) throws Exception {
        Event event = new Event();
        event.setPayload(objectMapper.writeValueAsString(payload));
        event.setType(payload.getClass().getName());
        eventRepository.save(event);
    }

    public List<EventDto> getEvents(Integer startId, Integer count) {
        List<Event> events = eventRepository.findAllByIdBetween(startId, startId + count - 1);
        return events.stream()
                .map(event -> new EventDto(event.getId(), event.getPayload(), event.getType()))
                .collect(Collectors.toList());
    }
}
