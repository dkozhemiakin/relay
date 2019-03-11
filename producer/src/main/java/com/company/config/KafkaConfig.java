package com.company.config;

import com.company.model.Metrics;
import com.company.properties.KafkaProperties;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
@EnableConfigurationProperties(KafkaProperties.class)
public class KafkaConfig {

  @Bean
  public ProducerFactory<UUID, Metrics> producerFactory(KafkaProperties kafkaProperties) {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

    return new DefaultKafkaProducerFactory<>(props);
  }

  @Bean
  public KafkaTemplate<UUID, Metrics> kafkaTemplate(ProducerFactory<UUID, Metrics> producerFactory) {
    return new KafkaTemplate<>(producerFactory);
  }

}