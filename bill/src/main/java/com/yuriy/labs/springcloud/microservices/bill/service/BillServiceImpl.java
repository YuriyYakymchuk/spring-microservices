package com.yuriy.labs.springcloud.microservices.bill.service;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.yuriy.labs.springcloud.microservices.bill.dao.BillRepository;
import com.yuriy.labs.springcloud.microservices.bill.messaging.channel.MyChannel;
import com.yuriy.labs.springcloud.microservices.bill.model.Bill;
import com.yuriy.labs.springcloud.microservices.common.AbstractBillCommand;
import com.yuriy.labs.springcloud.microservices.common.CreateBillCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RefreshScope
@Transactional
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Value("${custom.message}")
    private String message;

    @StreamListener(MyChannel.BILL)
    private void streamListener(AbstractBillCommand billCommand) {
        if(billCommand instanceof CreateBillCommand) {
            createBill(
                    billCommand.getTableId(),
                    ((CreateBillCommand) billCommand).getOrderId()
            );
        } else {
            payBills(billCommand.getTableId());
        }
    }

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
