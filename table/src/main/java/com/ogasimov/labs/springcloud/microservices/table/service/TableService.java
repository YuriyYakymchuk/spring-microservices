package com.ogasimov.labs.springcloud.microservices.table.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.ogasimov.labs.springcloud.microservices.common.AbstractTableCommand;
import com.ogasimov.labs.springcloud.microservices.common.FreeTableCommand;
import com.ogasimov.labs.springcloud.microservices.common.OccupyTableCommand;
import com.ogasimov.labs.springcloud.microservices.table.dao.TableRepository;
import com.ogasimov.labs.springcloud.microservices.table.model.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TableService {

    @Autowired
    private TableRepository tableRepository;

    @StreamListener
    public void streamListener(AbstractTableCommand tableCommand) {
        updateTable(tableCommand.getTableId(), tableCommand instanceof FreeTableCommand);
    }

    public List<Integer> getTableIds() {
        return tableRepository.findAll()
                .stream()
                .map(Table::getId)
                .collect(Collectors.toList());
    }

    public List<Integer> getTableIdsByOccupancy(Boolean occupancy) {
        return tableRepository.findAllByOccupancy(occupancy)
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
