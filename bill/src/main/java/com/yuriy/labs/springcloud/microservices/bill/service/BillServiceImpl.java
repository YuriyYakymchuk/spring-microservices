package com.yuriy.labs.springcloud.microservices.bill.service;

import com.yuriy.labs.springcloud.microservices.bill.dao.BillRepository;
import com.yuriy.labs.springcloud.microservices.bill.model.Bill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RefreshScope
@Transactional
@Slf4j
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private TableClient tableClient;

    @Value("${custom.message}")
    private String message;

    @Override
    public void createBill(Integer tableId, Integer orderId) {
        Bill bill = new Bill();
        bill.setTableId(tableId);
        bill.setOrderId(orderId);
        billRepository.save(bill);
    }

    @Override
    public void payBills(Integer tableId) {
        List<Bill> bills = billRepository.findAllByTableId(tableId);
        if (bills.isEmpty()) {
            throw  new EntityNotFoundException("Bills not found");
        }
        billRepository.delete(bills);
        tableClient.freeTable(tableId);
        log.info("Bill was paid for table {}", tableId);
    }

    @Override
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
