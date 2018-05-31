package com.yuriy.labs.springcloud.microservices.stock.dao;

import com.yuriy.labs.springcloud.microservices.stock.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    Stock findOneByMenuItemId(Integer menuItemId);
}
