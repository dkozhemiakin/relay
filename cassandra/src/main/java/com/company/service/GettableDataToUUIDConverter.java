package com.company.service;

import com.datastax.driver.core.GettableData;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GettableDataToUUIDConverter implements Converter<GettableData, UUID> {

  @Override
  public UUID convert(GettableData data) {
    return data.getUUID("id");
  }

}
