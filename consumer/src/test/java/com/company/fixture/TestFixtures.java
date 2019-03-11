package com.company.fixture;

import com.company.dao.MetricsDao;
import com.company.model.Device;
import com.company.model.Metrics;
import lombok.experimental.UtilityClass;

import java.util.Date;
import java.util.UUID;

@UtilityClass
public class TestFixtures {

  public Device getDevice() {
    return new Device(UUID.randomUUID().toString());
  }

  public Date getDate() {
    return new Date();
  }

  public Metrics getMetrics(Device device, Date date) {
    return new Metrics(device, date, 100);
  }

  public MetricsDao getMetricsDao(Device device, Date date) {
    return new MetricsDao(UUID.fromString(device.getId()), date, 100);
  }

}
