package com.ogasimov.labs.springcloud.microservices.bill.service;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.ogasimov.labs.springcloud.microservices.bill.dao.BillRepository;
import com.ogasimov.labs.springcloud.microservices.bill.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BillService {

    @Autowired
    private BillRepository billRepository;

    public void createBill(Integer tableId, Integer orderId) {
        Bill bill = new Bill();
        bill.setTableId(tableId);
        bill.setOrderId(orderId);
        billRepository.save(bill);
    }

    public void payBills(Integer tableId) {
        List<Bill> bills = billRepository.findAllByTableId(tableId);
        if (bills.isEmpty()) {
            throw  new EntityNotFoundException("Bills not found");
        }
        billRepository.delete(bills);
    }
}