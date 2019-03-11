package com.company.config;

import com.company.properties.CassandraProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableCassandraRepositories
@RequiredArgsConstructor
@EnableConfigurationProperties(CassandraProperties.class)
class CassandraConfig extends AbstractCassandraConfiguration {

  private final CassandraProperties cassandraProperties;

  @Override
  public String getContactPoints() {
    return cassandraProperties.getServers();
  }

  @Override
  protected int getPort() {
    return cassandraProperties.getPort();
  }

  @Override
  protected String getKeyspaceName() {
    return cassandraProperties.getKeyspace();
  }

  @Override
  protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
    return Collections.singletonList(
        CreateKeyspaceSpecification
            .createKeyspace(cassandraProperties.getKeyspace())
            .ifNotExists()
            .with(KeyspaceOption.DURABLE_WRITES, true)
            .withSimpleReplication()
    );
  }

}