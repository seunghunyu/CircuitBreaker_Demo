package com.cbr.ccbrkr.order.controller;

import com.cbr.ccbrkr.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 주문 리스트 확인을 위한 Controller
 */
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/orderlist")
    public String orderlist(){
        return orderService.getOrderList();
    }

    @GetMapping("/orders")
    public String orders(){
        String wantOrders = "AA,BB,CC";
        return wantOrders;
    }

}
