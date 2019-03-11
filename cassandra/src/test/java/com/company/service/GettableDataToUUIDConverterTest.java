package com.company.service;

import com.company.fixture.TestFixtures;
import com.datastax.driver.core.GettableData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class GettableDataToUUIDConverterTest {
  private static final UUID ID = TestFixtures.getDeviceId();

  @Mock
  private GettableData data;
  private GettableDataToUUIDConverter converter;

  @Before
  public void setUp() {
    converter = new GettableDataToUUIDConverter();
  }

  @Test
  public void testConvert() {
    Mockito.when(data.getUUID("id")).thenReturn(ID);

    UUID result = converter.convert(data);

    Assert.assertEquals(ID, result);
  }
}