package com.company.service;

import com.company.dao.MetricsDao;
import com.company.fixture.TestFixtures;
import com.company.model.Device;
import com.company.model.Metrics;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.UUID;

public class MetricsToDaoConverterTest {

  private static final Device DEVICE = TestFixtures.getDevice();
  private static final Date DATE = TestFixtures.getDate();
  private static final Metrics METRICS = TestFixtures.getMetrics(DEVICE, DATE);

  private MetricsToDaoConverter converter;

  @Before
  public void setUp() {
    converter = new MetricsToDaoConverter();
  }

  @Test
  public void testConvert() {
    MetricsDao result = converter.convert(METRICS);

    Assert.assertEquals(UUID.fromString(DEVICE.getId()), result.getDeviceId());
    Assert.assertEquals(DATE, result.getTimestamp());
    Assert.assertEquals(METRICS.getValue(), result.getValue(), 0.0000001);
  }

}