package com.yuriy.labs.springcloud.microservices.bill.dao;

import java.util.List;

import com.yuriy.labs.springcloud.microservices.bill.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
    List<Bill> findAllByTableId(Integer tableId);
}
