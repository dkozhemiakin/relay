package com.company.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class MetricsProducerRunner implements Runnable {

  private final MetricsProducer metricsProducer;
  private final int timeout;

  @Override
  public void run() {
    while (true) {
      metricsProducer.produce();
      try {
        Thread.sleep(timeout);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        break;
      }
    }
  }

}