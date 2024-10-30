package com.cbr.ccbrkr.order.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.print.attribute.standard.Media;

@Service
public class OrderService {
    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @Autowired
    private RestTemplate restTemplate;


    //org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
    //CircuitBreaker Factory 이용 방식
    public String getOrderList(){
        org.springframework.cloud.client.circuitbreaker.CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        //String url = "http://192.168.24.136:8080/orderlist";
        String url = "http://192.168.24.136:8080/orders";

        return circuitBreaker.run(() -> restTemplate.getForObject(url, String.class),
                throwable -> getDefaultOrderList());
    }

    public String getDefaultOrderList(){
        return "A,B,C";
    }

    @CircuitBreaker(name = "purchaseBreaker", fallbackMethod = "getDefaultPurchase")
    public String purchase(String param){
        String url = "http://192.168.20.52:29109";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestBody = param;
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        return restTemplate.postForObject(url, entity, String.class);
    }

    private String getDefaultPurchase(){
        return "purchase default list";
    }

}


