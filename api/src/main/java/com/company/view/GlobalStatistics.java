package com.company.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
public class GlobalStatistics {
  @JsonProperty("devices")
  private final List<UUID> devices;
  @JsonProperty("total_count")
  private final long totalCount;
}
