package com.company.fixture;

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

  public static Metrics getMetrics(Device device) {
    return new Metrics(device, new Date(), 100);
  }

}
