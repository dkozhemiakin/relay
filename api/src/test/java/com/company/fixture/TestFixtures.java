package com.company.fixture;

import com.company.dao.AggregateResults;
import com.company.view.DeviceStatistics;
import com.company.view.GlobalStatistics;
import lombok.experimental.UtilityClass;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@UtilityClass
public class TestFixtures {

  public UUID getDeviceId() {
    return UUID.randomUUID();
  }

  public DeviceStatistics getDeviceStatistics(UUID deviceUuid) {
    return DeviceStatistics
        .builder()
        .deviceId(deviceUuid)
        .build();
  }

  public DeviceStatistics getDeviceStatistics(UUID deviceUuid, Date from, Date to) {
    return DeviceStatistics
        .builder()
        .deviceId(deviceUuid)
        .from(from)
        .to(to)
        .build();
  }

  public GlobalStatistics getGlobalStatistics() {
    return new GlobalStatistics(Collections.singletonList(UUID.randomUUID()), 1);
  }

  public Date getDate() {
    return new Date();
  }

  public List<UUID> getDeviceIdList() {
    return Collections.singletonList(UUID.randomUUID());
  }

  public long getMetricsCount() {
    return 1L;
  }

  public AggregateResults getAggregateResults() {
    return new AggregateResults(1, 2, 3, 4);
  }

  public DeviceStatistics getDeviceStatistics(UUID deviceId, AggregateResults result, Date to, Date from) {
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

  public DeviceStatistics getDeviceStatistics(UUID deviceId, AggregateResults result) {
    return getDeviceStatistics(deviceId, result, null, null);
  }

}
