package com.cbr.ccbrkr.circuitbreaker.controller;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.vavr.collection.Seq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 현재 CircuitBreaker의 상태를 알 수 있는 REST API Controller
 */
@Slf4j
@RestController
public class CircuitBreakerController {

    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

    @GetMapping("/cbrk/close")
    public ResponseEntity<Void> close(@RequestParam String name) {
        circuitBreakerRegistry.circuitBreaker(name)
                .transitionToClosedState();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cbrk/open")
    public ResponseEntity<Void> open(@RequestParam String name) {
        circuitBreakerRegistry.circuitBreaker(name)
                .transitionToOpenState();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cbrk/status")
    public ResponseEntity<io.github.resilience4j.circuitbreaker.CircuitBreaker.State> status(@RequestParam String name) {
        io.github.resilience4j.circuitbreaker.CircuitBreaker.State state = circuitBreakerRegistry.circuitBreaker(name)
                .getState();
        return ResponseEntity.ok(state);
    }

//    @GetMapping("/cbrk/all")
//    public ResponseEntity<Void> all() {
//        Seq<CircuitBreaker> circuitBreakers = (Seq<CircuitBreaker>) circuitBreakerRegistry.getAllCircuitBreakers();
//        for (CircuitBreaker circuitBreaker : circuitBreakers) {
//            log.error("circuitName={}, state={}", circuitBreaker.getName(), circuitBreaker.getState());
//        }
//        return ResponseEntity.ok().build();
//    }

}
