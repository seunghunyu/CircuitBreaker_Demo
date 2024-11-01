package com.cbr.ccbrkr.order.service;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.pqc.crypto.ExchangePair;
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
@Slf4j
@Service
public class OrderService {

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderFallBackService orderFallBackService;

    //org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
    //CircuitBreaker Factory 이용 방식
    public String getOrderList(){
        org.springframework.cloud.client.circuitbreaker.CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        //String url = "http://192.168.24.136:8080/orderlist";
        //String url = "http://192.168.24.136:8080/orders";
        String url = "http://192.168.20.52:29109";
        return circuitBreaker.run(() -> restTemplate.getForObject(url, String.class),
                throwable -> getDefaultOrderList());
    }

    public String getDefaultOrderList(){
        return "A,B,C";
    }

    @CircuitBreaker(name = "purchaseBreaker", fallbackMethod = "getDefaultPurchase")
    public String purchase(String param){
        try {
            String url = "http://192.168.20.52:29109";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            String requestBody = param;
            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

            return restTemplate.postForObject(url, entity, String.class);
        }catch(Exception e){
            log.error("@@@@@@@@@@@@@@rest error="+e.getMessage());
            throw e;
        }

    }

    //예외처리 메소드는 CircuitBreaker 어노테이션이 붙어있는 메소드와 동일하게 파라미터가 붙어있어야 동작
    protected String getDefaultPurchase(String param, Throwable throwable){
        return orderFallBackService.getDefaultPurchase(param);
//        return "getDefaultPurchase";
    }

}


