package com.ogasimov.labs.springcloud.microservices.bill.service;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.ogasimov.labs.springcloud.microservices.bill.dao.BillRepository;
import com.ogasimov.labs.springcloud.microservices.bill.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@Service
@RefreshScope
@Transactional
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Value("${custom.message}")
    private String message;

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

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    public String getMessage() {
        return message;
    }
}
