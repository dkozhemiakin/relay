package com.company.service;

import com.company.dao.MetricsDao;
import com.company.fixture.TestFixtures;
import com.company.model.Device;
import com.company.model.Metrics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class QueueListenerTest {
  private static final Device DEVICE = TestFixtures.getDevice();
  private static final Date DATE = TestFixtures.getDate();
  private static final Metrics METRICS = TestFixtures.getMetrics(DEVICE, DATE);
  private static final MetricsDao METRICS_DAO = TestFixtures.getMetricsDao(DEVICE, DATE);

  @Mock
  private MetricsService metricsService;
  @Mock
  private MetricsToDaoConverter converter;
  @InjectMocks
  private QueueListener listener;

  @Test
  public void testReceive() {
    Mockito.when(converter.convert(METRICS)).thenReturn(METRICS_DAO);

    listener.receive(METRICS);

    Mockito.verify(metricsService).save(METRICS_DAO);
  }
}