package com.yuriy.labs.springcloud.microservices.table.controller;

import java.util.List;

import com.yuriy.labs.springcloud.microservices.table.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TableController {

    @Autowired
    private TableService tableService;

    @GetMapping("/tables")
    public List<Integer> getTables() {
        return tableService.getTableIds();
    }

    @GetMapping("/tables/{occupancy}")
    public List<Integer> getTablesByOccupancy(@PathVariable("occupancy") Boolean occupancy) {
        return tableService.getTableIdsByOccupancy(occupancy);
    }

    @PutMapping("/table/{id}/free")
    public void freeTable(@PathVariable Integer id) {
        tableService.updateTable(id, true);
    }

    @PutMapping("/table/{id}/occupy")
    public void occupyTable(@PathVariable Integer id) {
        tableService.updateTable(id, false);
    }
}
