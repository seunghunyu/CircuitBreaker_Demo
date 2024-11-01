package com.cbr.ccbrkr.order.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Configurable
public class OrderFallBackService {
    public String getDefaultPurchase(String param){
        return "purchase default list";
    }
}
