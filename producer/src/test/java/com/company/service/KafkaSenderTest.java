package com.company.service;

import com.company.model.Metrics;
import com.company.properties.KafkaProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class KafkaSenderTest {

  private static final String TOPIC = "mytopic";
  private static final Metrics METRICS = new Metrics();

  @Mock
  private KafkaTemplate<UUID, Metrics> template;
  @Mock
  private KafkaProperties kafkaProperties;
  @InjectMocks
  private KafkaSender kafkaSender;

  @Test
  public void testSend() {
    Mockito.when(kafkaProperties.getTopic()).thenReturn(TOPIC);

    kafkaSender.send(METRICS);

    Mockito.verify(template).send(TOPIC, METRICS);
  }

}