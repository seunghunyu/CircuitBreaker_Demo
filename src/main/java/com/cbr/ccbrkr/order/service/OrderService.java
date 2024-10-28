package com.cbr.ccbrkr.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @Autowired
    private RestTemplate restTemplate;

    public String getOrderList(){
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        //String url = "http://192.168.24.136:8080/orderlist";
        String url = "http://192.168.24.136:8080/orders";

        return circuitBreaker.run(() -> restTemplate.getForObject(url, String.class),
                throwable -> getDefaultOrderList());
    }

    public String getDefaultOrderList(){
        return "A,B,C";
    }


}
