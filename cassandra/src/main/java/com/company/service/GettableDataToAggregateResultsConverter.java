package com.company.service;

import com.company.dao.AggregateResults;
import com.datastax.driver.core.GettableData;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GettableDataToAggregateResultsConverter implements Converter<GettableData, AggregateResults> {

  @Override
  public AggregateResults convert(GettableData data) {
    return new AggregateResults(
        data.getLong("count"),
        data.getDouble("min"),
        data.getDouble("max"),
        data.getDouble("avg")
    );

  }

}
