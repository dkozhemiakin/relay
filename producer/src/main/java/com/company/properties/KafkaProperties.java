package com.company.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("kafka")
@Data
public class KafkaProperties {

  private String bootstrapServers;
  private String topic;

}
