package com.company.factory;

import com.company.service.KafkaSender;
import com.company.service.MetricsProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MetricsProducerFactory {

  private final MetricsFactory metricsFactory;
  private final DeviceFactory deviceFactory;
  private final KafkaSender kafkaSender;

  public MetricsProducer createMetricsProducer() {
    return new MetricsProducer(deviceFactory.createDevice(), metricsFactory, kafkaSender);
  }

}
