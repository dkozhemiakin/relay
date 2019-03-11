package com.company.fixture;

import com.company.dao.MetricsDao;
import lombok.experimental.UtilityClass;

import java.util.Date;
import java.util.UUID;

@UtilityClass
public class TestFixtures {

  public UUID getDeviceId() {
    return UUID.randomUUID();
  }

  public Date getDate() {
    return new Date();
  }

  public static MetricsDao getMetricsDao() {
    return new MetricsDao(getDeviceId(), new Date(), 100D);
  }

}
