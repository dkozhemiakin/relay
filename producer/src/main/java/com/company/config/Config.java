package com.company.config;

import com.company.properties.DeviceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@EnableConfigurationProperties(DeviceProperties.class)
public class Config {

  @Bean
  public ExecutorService executorService(DeviceProperties deviceProperties) {
    return Executors.newFixedThreadPool(deviceProperties.getAmount());
  }

}