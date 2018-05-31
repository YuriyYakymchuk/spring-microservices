package com.yuriy.labs.springcloud.microservices.stock.service;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.yuriy.labs.springcloud.microservices.common.AbstractStockCommand;
import com.yuriy.labs.springcloud.microservices.common.MinusStockCommand;
import com.yuriy.labs.springcloud.microservices.stock.dao.StockRepository;
import com.yuriy.labs.springcloud.microservices.stock.messaging.MyChannels;
import com.yuriy.labs.springcloud.microservices.stock.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @StreamListener(MyChannels.STOCK)
    public void streamListener(AbstractStockCommand stockCommand) {
        if (stockCommand instanceof MinusStockCommand) {
            minusFromStock(stockCommand.getMenuItems());
        }
    }

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
