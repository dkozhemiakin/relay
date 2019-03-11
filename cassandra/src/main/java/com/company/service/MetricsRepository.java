package com.company.service;

import com.company.dao.MetricsDao;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

@Repository
interface MetricsRepository extends CassandraRepository<MetricsDao, UUID> {

  @Query(value = "SELECT count(value) AS count, min(value) AS min, max(value) AS max, avg(value) AS avg " +
      "FROM metrics WHERE device_id=:deviceId AND date >= :from AND date <= :to")
  Object[] getStatistics(@Param("deviceId") UUID deviceId, @Param("from") Date from, @Param("to") Date to);

  @Query(value = "SELECT count(value) AS count, min(value) AS min, max(value) AS max, avg(value) AS avg " +
      "FROM metrics WHERE device_id=:deviceId")
  Object[] getStatistics(@Param("deviceId") UUID deviceId);

  @Query("SELECT DISTINCT device_id as id FROM metrics")
  Object[] getDevices();

  long count();

}
