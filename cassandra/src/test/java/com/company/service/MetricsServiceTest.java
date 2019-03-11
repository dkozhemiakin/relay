package com.company.service;

import com.company.dao.AggregateResults;
import com.company.dao.MetricsDao;
import com.company.fixture.TestFixtures;
import com.datastax.driver.core.GettableData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class MetricsServiceTest {

  private static final long COUNT = 1L;
  private static final double MIN = 2D;
  private static final double MAX = 3D;
  private static final double AVG = 4D;
  private static final AggregateResults AGGREGATE_RESULTS = new AggregateResults(COUNT, MIN, MAX, AVG);
  private static final MetricsDao METRICS_DAO = TestFixtures.getMetricsDao();
  private static final long METRICS_COUNT = 10L;
  private static final UUID DEVICE_ID = TestFixtures.getDeviceId();
  private static final Date DATE = TestFixtures.getDate();

  @Mock
  private GettableData data;
  @Mock
  private MetricsRepository repository;

  private MetricsService service;

  @Before
  public void setUp() {
    service = new MetricsService(repository, new GettableDataToAggregateResultsConverter(),
        new GettableDataToUUIDConverter());
  }

  @Test
  public void testSave() {
    service.save(METRICS_DAO);

    Mockito.verify(repository).save(METRICS_DAO);
  }

  @Test
  public void testGetMetricsCount() {
    Mockito.when(repository.count()).thenReturn(METRICS_COUNT);

    long result = service.getMetricsCount();

    Mockito.verify(repository).count();

    Assert.assertEquals(METRICS_COUNT, result);
  }

  @Test
  public void testGetDevices() {
    Mockito.when(data.getUUID("id")).thenReturn(DEVICE_ID);
    Mockito.when(repository.getDevices()).thenReturn(new Object[]{data});

    List<UUID> result = service.getDevices();

    Mockito.verify(repository).getDevices();

    Assert.assertEquals(1, result.size());
    Assert.assertEquals(DEVICE_ID, result.get(0));
  }

  @Test
  public void testGetDeviceStatistics() {
    Mockito.when(data.getLong("count")).thenReturn(COUNT);
    Mockito.when(data.getDouble("min")).thenReturn(MIN);
    Mockito.when(data.getDouble("max")).thenReturn(MAX);
    Mockito.when(data.getDouble("avg")).thenReturn(AVG);
    Mockito.when(repository.getStatistics(DEVICE_ID)).thenReturn(new Object[]{data});

    AggregateResults result = service.getDeviceStatistics(DEVICE_ID);

    Mockito.verify(repository).getStatistics(DEVICE_ID);

    Assert.assertEquals(AGGREGATE_RESULTS, result);
  }

  @Test
  public void testGetDeviceStatistics_withDates() {
    Mockito.when(data.getLong("count")).thenReturn(COUNT);
    Mockito.when(data.getDouble("min")).thenReturn(MIN);
    Mockito.when(data.getDouble("max")).thenReturn(MAX);
    Mockito.when(data.getDouble("avg")).thenReturn(AVG);
    Mockito.when(repository.getStatistics(DEVICE_ID, DATE, DATE)).thenReturn(new Object[]{data});

    AggregateResults result = service.getDeviceStatistics(DEVICE_ID, DATE, DATE);

    Mockito.verify(repository).getStatistics(DEVICE_ID, DATE, DATE);

    Assert.assertEquals(AGGREGATE_RESULTS, result);
  }

}