package com.yuriy.labs.springcloud.microservices.order.service;

import com.yuriy.labs.springcloud.microservices.order.dao.MenuItemRepository;
import com.yuriy.labs.springcloud.microservices.order.dao.OrderRepository;
import com.yuriy.labs.springcloud.microservices.order.model.MenuItem;
import com.yuriy.labs.springcloud.microservices.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StockClient stockClient;

    @Autowired
    private BillClient billClient;

    @Autowired
    private MenuItemRepository menuItemRepository;

    public Integer createOrder(Integer tableId, List<Integer> menuItems) {
        if(!isAllMenuItemsExist(menuItems)) {
            throw new EntityNotFoundException("Not all menu items are available.");
        }

        Order order = new Order();
        order.setTableId(tableId);
        orderRepository.save(order);

        final Integer orderId = order.getId();
        stockClient.minusFromStock(menuItems);
        billClient.createBill(tableId, orderId);

        return orderId;
    }

    private boolean isAllMenuItemsExist(final List<Integer> menuItemsIDs) {
        List<MenuItem> menuItems = menuItemRepository.findAllById(menuItemsIDs);
        Set<Integer> existingMenuItems = menuItems.stream()
                .map(MenuItem::getId)
                .collect(Collectors.toSet());
        return existingMenuItems.containsAll(menuItemsIDs);
    }
}
