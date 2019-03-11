package com.company.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("device")
@Data
public class DeviceProperties {

  private int amount;
  private int timeout;

}
