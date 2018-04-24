package com.ogasimov.labs.springcloud.microservices.guest;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient("Table")
public interface TableClient {

    @GetMapping("/tables")
    List<Integer> getFreeTables();

    @PutMapping("/table/{id}/free")
    void freeTable(@PathVariable Integer id);

    @PutMapping("/table/{id}/occupy")
    void occupyTable(@PathVariable Integer id);

}
