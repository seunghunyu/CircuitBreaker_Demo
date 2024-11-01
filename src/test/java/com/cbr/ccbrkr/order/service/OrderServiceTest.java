package com.cbr.ccbrkr.order.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
//@SpringBootTest
public class OrderServiceTest {
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Autowired
//    private OrderFallBackService orderFallBackService;
//
//    @Autowired
//    private Environment env;
//
//    @Test
//    public void purchase(){
//        String result = purchaseWithCircuitBreaker("");
//        log.info("Result="+result);
//    }
//
//    @CircuitBreaker(name = "purchaseBreaker", fallbackMethod = "getDefaultPurchase")
//    public String purchaseWithCircuitBreaker(String requestBody){
//        String url = "http://192.168.20.52:29109";
//
//        HttpEntity<String> entity = null;
//
//        String property = env.getProperty("resilience4j.circuitbreaker.configs.default.slidingWindowSize");
//
//        log.info("property="+ property);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        entity = new HttpEntity<>(requestBody, headers);
//        return restTemplate.postForObject(url, entity, String.class);
////        try {
////
////            HttpHeaders headers = new HttpHeaders();
////            headers.setContentType(MediaType.APPLICATION_JSON);
////            entity = new HttpEntity<>(requestBody, headers);
////            return restTemplate.postForObject(url, entity, String.class);
////        }catch(Exception e){
////            log.error("@@@@@@@@@@@@@@rest error="+e.getMessage());
////            throw e;
////        }
//    }
//
//    private String getDefaultPurchase(String param, Throwable throwable){
//        return orderFallBackService.getDefaultPurchase(param);
//    }
}