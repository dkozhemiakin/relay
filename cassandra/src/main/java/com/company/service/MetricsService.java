package com.company.service;

import com.company.dao.AggregateResults;
import com.company.dao.MetricsDao;
import com.datastax.driver.core.GettableData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MetricsService {

  private final MetricsRepository repository;
  private final GettableDataToAggregateResultsConverter aggregateResultsConverter;
  private final GettableDataToUUIDConverter uuidConverter;

  AggregateResults getDeviceStatistics(UUID deviceId, Date from, Date to) {
    Object[] statistics = repository.getStatistics(deviceId, from, to);
    return aggregateResultsConverter.convert((GettableData) statistics[0]);
  }

  AggregateResults getDeviceStatistics(UUID deviceId) {
    Object[] statistics = repository.getStatistics(deviceId);
    return aggregateResultsConverter.convert((GettableData) statistics[0]);
  }

  void save(MetricsDao metricsDao) {
    repository.save(metricsDao);
  }

  List<UUID> getDevices() {
    return Arrays
        .stream(repository.getDevices())
        .map(item -> uuidConverter.convert((GettableData) item))
        .collect(Collectors.toList());
  }

  long getMetricsCount() {
    return repository.count();
  }

}
