package com.company.dao;

import com.datastax.driver.core.DataType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Table("metrics")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetricsDao {

  @PrimaryKeyColumn(name = "device_id", type = PrimaryKeyType.PARTITIONED)
  @CassandraType(type = DataType.Name.UUID)
  private UUID deviceId;
  @PrimaryKeyColumn(name = "date", type = PrimaryKeyType.CLUSTERED)
  private Date timestamp;
  @Column("value")
  private double value;

}
