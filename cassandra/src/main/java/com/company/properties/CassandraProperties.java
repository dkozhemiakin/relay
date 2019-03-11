package com.company.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("cassandra")
@Data
public class CassandraProperties {

  private String servers;
  private int port;
  private String keyspace;

}
