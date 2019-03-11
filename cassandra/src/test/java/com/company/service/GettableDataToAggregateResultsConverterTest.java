package com.company.service;

import com.company.dao.AggregateResults;
import com.datastax.driver.core.GettableData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GettableDataToAggregateResultsConverterTest {

  private static final long COUNT = 1L;
  private static final double MIN = 2D;
  private static final double MAX = 3D;
  private static final double AVG = 4D;
  private static final AggregateResults AGGREGATE_RESULTS = new AggregateResults(COUNT, MIN, MAX, AVG);

  @Mock
  private GettableData data;
  private GettableDataToAggregateResultsConverter converter;

  @Before
  public void setUp() {
    converter = new GettableDataToAggregateResultsConverter();
  }

  @Test
  public void testConvert() {
    Mockito.when(data.getLong("count")).thenReturn(COUNT);
    Mockito.when(data.getDouble("min")).thenReturn(MIN);
    Mockito.when(data.getDouble("max")).thenReturn(MAX);
    Mockito.when(data.getDouble("avg")).thenReturn(AVG);

    AggregateResults result = converter.convert(data);

    Assert.assertEquals(AGGREGATE_RESULTS, result);
  }

}