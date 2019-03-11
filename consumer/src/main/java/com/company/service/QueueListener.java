package com.company.service;

import com.company.model.Metrics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class QueueListener {

  private final MetricsService metricsService;
  private final MetricsToDaoConverter converter;

  @KafkaListener(topics = "${kafka.topic}")
  public void receive(@Payload Metrics metrics) {
    log.info("Received {}", metrics);
    metricsService.save(converter.convert(metrics));
  }

}