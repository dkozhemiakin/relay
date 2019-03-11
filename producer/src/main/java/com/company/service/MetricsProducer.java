package com.company.service;

import com.company.factory.MetricsFactory;
import com.company.model.Device;
import com.company.model.Metrics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class MetricsProducer {

  private final Device device;
  private final MetricsFactory metricsFactory;
  private final KafkaSender kafkaSender;

  public void produce() {
    Metrics metrics = metricsFactory.createMetrics(device);
    log.info("Sending {}", metrics);
    kafkaSender.send(metrics);
  }

}