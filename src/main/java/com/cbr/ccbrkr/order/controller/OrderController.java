package com.cbr.ccbrkr.order.controller;

import com.cbr.ccbrkr.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/orderlist")
    public String orderlist(){
        return orderService.getOrderList();
    }


}
