package com.yuriy.labs.springcloud.microservices.guest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Slf4j
public class DinnerService {

    @Autowired
    private TableClient tableClient;

    @Autowired
    private OrderClient orderClient;

    @Autowired
    @Qualifier("BillClient")
    private BillClient billClient;

    @Autowired
    private OAuth2RestOperations oAuth2RestTemplate;

    private String token;

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

    public String finishDinner(Integer tableId) {
        return billClient.payBills(token, tableId);
    }

    public String testCircuitBreaker() {
        return billClient.throwException(token);
    }

    @PostConstruct
    private void setToken() {
        OAuth2AccessToken oAuth2AccessToken = oAuth2RestTemplate.getAccessToken();
        token = oAuth2AccessToken.getTokenType() + " " + oAuth2AccessToken.getValue();
    }
}
