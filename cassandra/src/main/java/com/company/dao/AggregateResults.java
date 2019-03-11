package com.company.dao;

import lombok.Value;

@Value
public class AggregateResults {
  private final long count;
  private final double min;
  private final double max;
  private final double avg;
}
