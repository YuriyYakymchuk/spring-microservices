package com.yuriy.labs.springcloud.microservices.order.service;

import java.util.Map;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

import com.yuriy.labs.springcloud.microservices.order.dao.MenuItemRepository;
import com.yuriy.labs.springcloud.microservices.order.model.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MenuService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    public Map<Integer, String> getMenuItems() {
        return menuItemRepository.findAll()
                .stream()
                .collect(Collectors.toMap(MenuItem::getId, MenuItem::getName));
    }
}
