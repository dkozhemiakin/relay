package com.company.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.Date;
import java.util.UUID;

@Value
@Builder
public class DeviceStatistics {
  @JsonProperty("device_id")
  private final UUID deviceId;
  @JsonProperty("from_date")
  private final Date from;
  @JsonProperty("to_date")
  private final Date to;
  @JsonProperty("count")
  private final long count;
  @JsonProperty("min_value")
  private final double min;
  @JsonProperty("max_value")
  private final double max;
  @JsonProperty("avg_value")
  private final double avg;

}
