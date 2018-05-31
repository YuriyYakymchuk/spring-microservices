package com.yuriy.labs.springcloud.microservices.guest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuriy.labs.springcloud.microservices.common.*;
import com.yuriy.labs.springcloud.microservices.guest.messaging.MySources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DinnerService {

    @Autowired
    private TableClient tableClient;

    @Autowired
    private EventClient eventClient;

    @Autowired
    private MySources sources;

    @Autowired
    private ObjectMapper objectMapper;

    public Integer startDinner(List<Integer> menuItems) {
        //check free tables
        List<Integer> freeTables = tableClient.getTablesByOccupancy(true);
        if (freeTables.size() == 0) {
            throw new RuntimeException("No free tables available.");
        }

        //occupy a table
        final Integer tableId = freeTables.get(0);

        sources.event().send(
            MessageBuilder.withPayload(new StartDinnerCommand(tableId, menuItems)).build()
        );

        return occupyTableAndCreateOrder(tableId, menuItems);
    }

    private Integer occupyTableAndCreateOrder(Integer tableId, List<Integer> menuItems) {
        sources.table().send(
            MessageBuilder.withPayload(new OccupyTableCommand(tableId)).build()
        );
        sources.order().send(
            MessageBuilder.withPayload(new CreateOrderCommand(tableId, menuItems)).build()
        );
        return tableId;
    }

    public void finishDinner(Integer tableId) {
        sources.event().send(
            MessageBuilder.withPayload(new FinishDinnerCommand(tableId)).build()
        );

        payBillAndFreeTable(tableId);
    }

    public void payBillAndFreeTable(Integer tableId) {
        sources.bill().send(
            MessageBuilder.withPayload(new PayBillCommand(tableId)).build()
        );

        sources.table().send(
            MessageBuilder.withPayload(new FreeTableCommand(tableId)).build()
        );
    }

    public void rebuildState() {
        clearState();
        replayEvents();
    }

    private void replayEvents() {
        Integer lastProcessedEventId = 0;
        Integer count = 5;

        List<EventDto> events;
        do {
            events = eventClient.getEvents(lastProcessedEventId + 1, count);
            lastProcessedEventId = replayEvents(events);
        } while (events.size() == count);
    }

    private Integer replayEvents(List<EventDto> events) {
        events.stream().forEach(event -> {
            try {
                Object command = objectMapper.readValue(event.getPayload(), Class.forName(event.getType()));

                if (command instanceof StartDinnerCommand) {
                    StartDinnerCommand startCommand = (StartDinnerCommand) command;
                    occupyTableAndCreateOrder(startCommand.getTableId(), startCommand.getMenuItems());

                } else if (command instanceof FinishDinnerCommand) {
                    FinishDinnerCommand finishCommand = (FinishDinnerCommand) command;
                    payBillAndFreeTable(finishCommand.getTableId());

                }

            } catch (Exception ex) {
                System.err.println("Something went wrong");
            }
        });

        return events.get(events.size() - 1).getId();
    }

    private void clearState() {
        List<Integer> occupiedTables = tableClient.getTablesByOccupancy(false);

        occupiedTables.forEach(this::payBillAndFreeTable);
    }
}
