package com.company.controller;

import com.company.fixture.TestFixtures;
import com.company.service.StatisticsService;
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
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsControllerTest {

  private static final GlobalStatistics GLOBAL_STATISTICS = TestFixtures.getGlobalStatistics();
  private static final UUID DEVICE_ID = TestFixtures.getDeviceId();
  private static final DeviceStatistics DEVICE_STATISTICS = TestFixtures.getDeviceStatistics(DEVICE_ID);
  private static final Date DATE = TestFixtures.getDate();
  private static final DeviceStatistics DEVICE_STATISTICS_DATES = TestFixtures
      .getDeviceStatistics(DEVICE_ID, DATE, DATE);

  @Mock
  private StatisticsService service;

  @InjectMocks
  private StatisticsController controller;

  @Test
  public void testGetGlobalStatistics() {
    Mockito.when(service.getGlobalStatistics()).thenReturn(GLOBAL_STATISTICS);

    GlobalStatistics result = controller.getGlobalStatistics();

    Mockito.verify(service).getGlobalStatistics();

    Assert.assertEquals(GLOBAL_STATISTICS, result);
  }

  @Test
  public void testGetStatistics_noDates() {
    Mockito.when(service.getStatistics(DEVICE_ID)).thenReturn(DEVICE_STATISTICS);

    DeviceStatistics result = controller.getStatisticsByDeviceId(DEVICE_ID, null, null);

    Mockito.verify(service).getStatistics(DEVICE_ID);

    Assert.assertEquals(DEVICE_STATISTICS, result);
  }

  @Test
  public void testGetStatistics_dates() {
    Mockito.when(service.getStatistics(DEVICE_ID, DATE, DATE)).thenReturn(DEVICE_STATISTICS_DATES);

    DeviceStatistics result = controller.getStatisticsByDeviceId(DEVICE_ID, DATE, DATE);

    Mockito.verify(service).getStatistics(DEVICE_ID, DATE, DATE);

    Assert.assertEquals(DEVICE_STATISTICS_DATES, result);
  }

  @Test
  public void testGetStatistics_firstDateMissing() {
    Mockito.when(service.getStatistics(DEVICE_ID)).thenReturn(DEVICE_STATISTICS);

    DeviceStatistics result = controller.getStatisticsByDeviceId(DEVICE_ID, null, DATE);

    Mockito.verify(service).getStatistics(DEVICE_ID);

    Assert.assertEquals(DEVICE_STATISTICS, result);
  }

  @Test
  public void testGetStatistics_secondDateMissing() {
    Mockito.when(service.getStatistics(DEVICE_ID)).thenReturn(DEVICE_STATISTICS);

    DeviceStatistics result = controller.getStatisticsByDeviceId(DEVICE_ID, DATE, null);

    Mockito.verify(service).getStatistics(DEVICE_ID);

    Assert.assertEquals(DEVICE_STATISTICS, result);
  }

}