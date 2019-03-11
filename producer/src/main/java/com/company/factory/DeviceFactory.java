package com.company.factory;

import com.company.model.Device;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeviceFactory {

  public Device createDevice() {
    return new Device(UUID.randomUUID().toString());
  }

}
