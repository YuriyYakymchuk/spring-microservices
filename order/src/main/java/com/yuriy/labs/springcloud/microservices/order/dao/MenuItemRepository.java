package com.yuriy.labs.springcloud.microservices.order.dao;

import com.yuriy.labs.springcloud.microservices.order.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {
}
