package com.yuriy.labs.springcloud.microservices.guest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DinnerService {

    @Autowired
    private TableClient tableClient;

    @Autowired
    private OrderClient orderClient;

    @Autowired
  //  @Qualifier("Bill")
    private BillClient billClient;

    public Integer startDinner(List<Integer> menuItems) {
        //check free tables
        List<Integer> freeTables = tableClient.getTablesByOccupancy(true);
        log.info("Free tables: {}", freeTables);
        if (freeTables.size() == 0) {
            throw new RuntimeException("No free tables available.");
        }

        //occupy a table
        final Integer tableId = freeTables.get(0);
        tableClient.occupyTable(tableId);
        log.info("Table: {} was occupied.", tableId);

        //create the order
        orderClient.createOrder(tableId, menuItems);
        log.info("Order for table: {} with menu items: {} was created.", tableId, menuItems);

        return tableId;
    }

    public void finishDinner(Integer tableId) {
        log.info("Response from the Bill client: {}", billClient.payBills(tableId));
    }

    public void testCircuitBreaker() {
        billClient.triggerError();
    }

}
