package com.yuriy.labs.springcloud.microservices.bill.service;

import com.yuriy.labs.springcloud.microservices.bill.model.Bill;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface BillService {

    @PreAuthorize("hasAuthority('ADMIN_WRITE')")
    String getMessage();

    void createBill(Integer tableId, Integer orderId);

    void payBills(Integer tableId);

    List<Bill> getAllBills();
}
