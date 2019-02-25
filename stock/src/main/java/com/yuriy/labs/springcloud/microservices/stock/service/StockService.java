package com.yuriy.labs.springcloud.microservices.stock.service;

import com.yuriy.labs.springcloud.microservices.stock.dao.StockRepository;
import com.yuriy.labs.springcloud.microservices.stock.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public void minusFromStock(List<Integer> menuItems) {
        menuItems.forEach(menuItemId -> {
            Stock stock = stockRepository.findOneByMenuItemId(menuItemId);
            if (stock == null) {
                throw  new EntityNotFoundException("Stock not found: " + menuItemId);
            }
            if (stock.getCount() == 0) {
                throw  new EntityNotFoundException("Stock empty: " + menuItemId);
            }
            stock.setCount(stock.getCount() - 1);
            stockRepository.save(stock);
        });
    }
}
