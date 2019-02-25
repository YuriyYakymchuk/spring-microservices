package com.yuriy.labs.springcloud.microservices.bill.service;

import com.yuriy.labs.springcloud.microservices.bill.model.Bill;

import java.util.List;

public interface BillService {

    String getMessage();

    void createBill(Integer tableId, Integer orderId);

    void payBills(Integer tableId);

    List<Bill> getAllBills();
}
