package com.yuriy.labs.springcloud.microservices.table.service;

import com.yuriy.labs.springcloud.microservices.table.dao.TableRepository;
import com.yuriy.labs.springcloud.microservices.table.model.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TableService {

    @Autowired
    private TableRepository tableRepository;

    public List<Integer> getTableIds() {
        return tableRepository.findAll()
                .stream()
                .map(Table::getId)
                .collect(Collectors.toList());
    }

    public List<Integer> getTableIdsByOccupancy(Boolean occupancy) {
        return tableRepository.findAllByFree(occupancy)
                .stream()
                .map(Table::getId)
                .collect(Collectors.toList());
    }

    public void updateTable(Integer id, boolean isFree) {
        Table table = tableRepository.findOne(id);
        if (table == null) {
            throw  new EntityNotFoundException("Table not found");
        }
        table.setFree(isFree);
        tableRepository.save(table);
    }
}
