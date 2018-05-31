package com.yuriy.labs.springcloud.microservices.table.dao;

import java.util.List;

import com.yuriy.labs.springcloud.microservices.table.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<Table, Integer> {

    List<Table> findAllByFree(Boolean free);

}
