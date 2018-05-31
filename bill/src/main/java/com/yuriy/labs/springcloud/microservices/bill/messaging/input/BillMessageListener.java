package com.yuriy.labs.springcloud.microservices.bill.messaging.input;

import com.yuriy.labs.springcloud.microservices.bill.dao.BillRepository;
import com.yuriy.labs.springcloud.microservices.bill.messaging.channel.MyChannel;
import com.yuriy.labs.springcloud.microservices.bill.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
public class BillMessageListener {

    private final BillRepository billRepository;

    @Autowired
    public BillMessageListener(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @StreamListener(target = MyChannel.BILL)
    public void createBill(String message) {
        String[] billInfo = message.replace("\"","").split(" ");

        Bill bill = new Bill();
        bill.setTableId(Integer.valueOf(billInfo[0]));
        bill.setOrderId(Integer.valueOf(billInfo[1]));
        billRepository.save(bill);
    }
}
