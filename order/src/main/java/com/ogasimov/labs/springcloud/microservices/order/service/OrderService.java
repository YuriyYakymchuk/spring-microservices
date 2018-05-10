package com.ogasimov.labs.springcloud.microservices.order.service;

import com.ogasimov.labs.springcloud.microservices.common.AbstractOrderCommand;
import com.ogasimov.labs.springcloud.microservices.common.CreateBillCommand;
import com.ogasimov.labs.springcloud.microservices.common.CreateOrderCommand;
import com.ogasimov.labs.springcloud.microservices.common.MinusStockCommand;
import com.ogasimov.labs.springcloud.microservices.order.dao.OrderRepository;
import com.ogasimov.labs.springcloud.microservices.order.messaging.MyChannels;
import com.ogasimov.labs.springcloud.microservices.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MyChannels myChannels;

    @StreamListener(MyChannels.ORDER)
    public void streamListener(AbstractOrderCommand orderCommand) {
        if (orderCommand instanceof CreateOrderCommand) {
            createOrder(
                    orderCommand.getTableId(),
                    ((CreateOrderCommand) orderCommand).getMenuItems()
            );
        }
    }

    public Integer createOrder(Integer tableId, List<Integer> menuItems) {
        Order order = new Order();
        order.setTableId(tableId);
        orderRepository.save(order);

        final Integer orderId = order.getId();

        myChannels.stock().send(
            MessageBuilder.withPayload(new MinusStockCommand(menuItems)).build()
        );

        myChannels.bill().send(
            MessageBuilder.withPayload(new CreateBillCommand(tableId, orderId)).build()
        );

        return orderId;
    }
}
