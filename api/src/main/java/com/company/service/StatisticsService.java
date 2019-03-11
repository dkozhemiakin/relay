package com.company.service;

import com.company.dao.AggregateResults;
import com.company.view.DeviceStatistics;
import com.company.view.GlobalStatistics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StatisticsService {

  private final MetricsService metricsService;

  public DeviceStatistics getStatistics(UUID deviceId) {
    AggregateResults result = metricsService.getDeviceStatistics(deviceId);
    return buildResult(deviceId, result);
  }

  public DeviceStatistics getStatistics(UUID deviceId, Date from, Date to) {
    AggregateResults result = metricsService.getDeviceStatistics(deviceId, from, to);
    return buildResult(deviceId, result, to, from);
  }

  public GlobalStatistics getGlobalStatistics() {
    return new GlobalStatistics(
        metricsService.getDevices(),
        metricsService.getMetricsCount()
    );
  }

  private DeviceStatistics buildResult(UUID deviceId, AggregateResults result, Date to, Date from) {
    return DeviceStatistics
        .builder()
        .from(from)
        .to(to)
        .deviceId(deviceId)
        .count(result.getCount())
        .min(result.getMin())
        .max(result.getMax())
        .avg(result.getAvg())
        .build();
  }

  private DeviceStatistics buildResult(UUID deviceId, AggregateResults result) {
    return buildResult(deviceId, result, null, null);
  }

}
