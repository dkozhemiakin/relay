package com.company;

import com.company.factory.MetricsProducerRunnerFactory;
import com.company.properties.DeviceProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.stream.IntStream;

@Slf4j
@RequiredArgsConstructor
@Component
public class Runner implements CommandLineRunner {

  private final DeviceProperties deviceProperties;
  private final MetricsProducerRunnerFactory metricsProducerRunnerFactory;
  private final ExecutorService executorService;

  @Override
  public void run(String... args) {
    log.info("Producer app started!");

    IntStream
        .range(0, deviceProperties.getAmount())
        .mapToObj(index -> metricsProducerRunnerFactory.createMetricsProducerRunner())
        .forEach(executorService::submit);
  }

}
