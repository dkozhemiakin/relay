package com.company.factory;

import com.company.model.Device;
import com.company.model.Metrics;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Component
public class MetricsFactory {

  private static final Random RANDOM = new Random();
  private static final int BOUND = 100;

  public Metrics createMetrics(Device device) {
    return new Metrics(device, new Date(), RANDOM.nextInt(BOUND));
  }

}
