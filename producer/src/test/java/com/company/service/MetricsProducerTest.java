package com.company.service;

import com.company.factory.MetricsFactory;
import com.company.fixture.TestFixtures;
import com.company.model.Device;
import com.company.model.Metrics;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MetricsProducerTest {

  private static final Device DEVICE = TestFixtures.getDevice();
  private static final Metrics METRICS = TestFixtures.getMetrics(DEVICE);

  @Mock
  private MetricsFactory metricsFactory;
  @Mock
  private KafkaSender kafkaSender;

  private MetricsProducer metricsProducer;

  @Before
  public void setUp() {
    metricsProducer = new MetricsProducer(DEVICE, metricsFactory, kafkaSender);
  }

  @Test
  public void testRun() {
    Mockito.when(metricsFactory.createMetrics(DEVICE)).thenReturn(METRICS);

    metricsProducer.produce();

    Mockito.verify(kafkaSender).send(METRICS);
  }

}