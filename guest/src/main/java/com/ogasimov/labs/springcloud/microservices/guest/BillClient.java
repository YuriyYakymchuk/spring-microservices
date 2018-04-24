package com.ogasimov.labs.springcloud.microservices.guest;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;

@FeignClient("Bill")
public interface BillClient {

    @DeleteMapping("/bills/{tableId}")
    void payBills(Integer tableId);

}
