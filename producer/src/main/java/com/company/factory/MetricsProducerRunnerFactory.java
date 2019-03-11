package com.company.factory;

import com.company.properties.DeviceProperties;
import com.company.service.MetricsProducerRunner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MetricsProducerRunnerFactory {

  private final DeviceProperties deviceProperties;
  private final MetricsProducerFactory metricsProducerFactory;

  public MetricsProducerRunner createMetricsProducerRunner() {
    return new MetricsProducerRunner(metricsProducerFactory.createMetricsProducer(), deviceProperties.getTimeout());
  }

}
