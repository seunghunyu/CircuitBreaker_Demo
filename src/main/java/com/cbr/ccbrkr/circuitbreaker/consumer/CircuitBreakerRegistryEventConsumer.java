package com.cbr.ccbrkr.circuitbreaker.consumer;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.core.registry.EntryAddedEvent;
import io.github.resilience4j.core.registry.EntryRemovedEvent;
import io.github.resilience4j.core.registry.EntryReplacedEvent;
import io.github.resilience4j.core.registry.RegistryEventConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CircuitBreakerRegistryEventConsumer implements RegistryEventConsumer<CircuitBreaker> {
    @Override
    public void onEntryAddedEvent(EntryAddedEvent<CircuitBreaker> entryAddedEvent) {  //새로운 엔트리가 추가될 때 호출, 초기화 및 로깅용도
        entryAddedEvent.getAddedEntry().getEventPublisher()
                .onFailureRateExceeded(event -> log.warn("{} failure rate {}%",
                        event.getCircuitBreakerName(),event.getFailureRate()))
                .onError(event -> event.getCircuitBreakerName())
                .onStateTransition(event -> log.info("{} state {} -> {}",
                        event.getCircuitBreakerName(), event.getStateTransition().getFromState(), event.getStateTransition().getToState()));
    }

    @Override
    public void onEntryRemovedEvent(EntryRemovedEvent<CircuitBreaker> entryRemoveEvent) { //엔트리가 제거될 때 호출, 정리 및 로깅용도
        log.info("Circuit breaker {} removed", entryRemoveEvent.getRemovedEntry().getName());
    }

    @Override
    public void onEntryReplacedEvent(EntryReplacedEvent<CircuitBreaker> entryReplacedEvent) { //기존 엔트리가 교체될 때 호출, 새로운 설정에 대한 로깅 용도
        log.info("Circuit breaker {} replaced", entryReplacedEvent.getNewEntry().getName());
    }

}
