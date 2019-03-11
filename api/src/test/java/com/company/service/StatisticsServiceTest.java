package com.company.service;

import com.company.dao.AggregateResults;
import com.company.fixture.TestFixtures;
import com.company.view.DeviceStatistics;
import com.company.view.GlobalStatistics;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsServiceTest {

  private static final List<UUID> DEVICE_ID_LIST = TestFixtures.getDeviceIdList();
  private static final long METRICS_COUNT = TestFixtures.getMetricsCount();
  private static final GlobalStatistics GLOBAL_STATISTICS = new GlobalStatistics(DEVICE_ID_LIST, METRICS_COUNT);
  private static final UUID DEVICE_ID = TestFixtures.getDeviceId();
  private static final AggregateResults AGGREGATE_RESULTS = TestFixtures.getAggregateResults();
  private static final DeviceStatistics DEVICE_STATISTICS = TestFixtures.getDeviceStatistics(DEVICE_ID, AGGREGATE_RESULTS);
  private static final Date DATE = TestFixtures.getDate();
  private static final DeviceStatistics DEVICE_STATISTICS_DATES =
      TestFixtures.getDeviceStatistics(DEVICE_ID, AGGREGATE_RESULTS, DATE, DATE);

  @Mock
  private MetricsService metricsService;

  @InjectMocks
  private StatisticsService statisticsService;

  @Test
  public void testGetGlobalStatistics() {
    Mockito.when(metricsService.getDevices()).thenReturn(DEVICE_ID_LIST);
    Mockito.when(metricsService.getMetricsCount()).thenReturn(METRICS_COUNT);

    GlobalStatistics result = statisticsService.getGlobalStatistics();

    Mockito.verify(metricsService).getDevices();
    Mockito.verify(metricsService).getMetricsCount();

    Assert.assertEquals(GLOBAL_STATISTICS, result);
  }

  @Test
  public void testGetStatistics() {
    Mockito.when(metricsService.getDeviceStatistics(DEVICE_ID)).thenReturn(AGGREGATE_RESULTS);

    DeviceStatistics result = statisticsService.getStatistics(DEVICE_ID);

    Mockito.verify(metricsService).getDeviceStatistics(DEVICE_ID);

    Assert.assertEquals(DEVICE_STATISTICS, result);
  }

  @Test
  public void testGetStatistics_dates() {
    Mockito.when(metricsService.getDeviceStatistics(DEVICE_ID, DATE, DATE)).thenReturn(AGGREGATE_RESULTS);

    DeviceStatistics result = statisticsService.getStatistics(DEVICE_ID, DATE, DATE);

    Mockito.verify(metricsService).getDeviceStatistics(DEVICE_ID, DATE, DATE);

    Assert.assertEquals(DEVICE_STATISTICS_DATES, result);
  }


}