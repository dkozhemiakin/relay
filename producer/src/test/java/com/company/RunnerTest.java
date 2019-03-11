package com.company;

import com.company.factory.MetricsProducerRunnerFactory;
import com.company.properties.DeviceProperties;
import com.company.service.MetricsProducer;
import com.company.service.MetricsProducerRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.ExecutorService;

@RunWith(MockitoJUnitRunner.class)
public class RunnerTest {

  private static final int AMOUNT = 10;

  @Mock
  private DeviceProperties deviceProperties;
  @Mock
  private MetricsProducerRunnerFactory metricsProducerRunnerFactory;
  @Mock
  private ExecutorService executorService;
  @Mock
  private MetricsProducerRunner metricsProducerRunner;
  @InjectMocks
  private Runner runner;

  @Test
  public void testRun() {
    Mockito.when(deviceProperties.getAmount()).thenReturn(AMOUNT);
    Mockito.when(metricsProducerRunnerFactory.createMetricsProducerRunner()).thenReturn(metricsProducerRunner);

    runner.run();

    Mockito.verify(metricsProducerRunnerFactory, Mockito.times(AMOUNT)).createMetricsProducerRunner();
    Mockito.verify(executorService, Mockito.times(AMOUNT)).submit(ArgumentMatchers.any(MetricsProducerRunner.class));
  }

}