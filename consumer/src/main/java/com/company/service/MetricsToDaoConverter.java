package com.company.service;

import com.company.dao.MetricsDao;
import com.company.model.Metrics;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MetricsToDaoConverter implements Converter<Metrics, MetricsDao> {

  @Override
  public MetricsDao convert(Metrics metrics) {
    return new MetricsDao(
        UUID.fromString(metrics.getDevice().getId()),
        metrics.getTimestamp(),
        metrics.getValue()
    );
  }

}
