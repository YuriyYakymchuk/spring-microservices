package com.yuriy.labs.springcloud.microservices.bill.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient("Table")
public interface TableClient {

    @GetMapping("/tables")
    List<Integer> getTables();

    @GetMapping("/tables/{occupancy}")
    List<Integer> getTablesByOccupancy(@PathVariable("occupancy") Boolean occupancy);

    @PutMapping("/table/{id}/free")
    void freeTable(@PathVariable("id") Integer id);

    @PutMapping("/table/{id}/occupy")
    void occupyTable(@PathVariable("id") Integer id);

}
