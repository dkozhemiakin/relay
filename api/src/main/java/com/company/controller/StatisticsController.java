package com.company.controller;

import com.company.service.StatisticsService;
import com.company.view.DeviceStatistics;
import com.company.view.GlobalStatistics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StatisticsController {

  private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

  private final StatisticsService service;

  @GetMapping("/statistics")
  GlobalStatistics getGlobalStatistics() {
    log.info("Requested global statistics");
    return service.getGlobalStatistics();
  }

  @GetMapping("/statistics/{deviceId}")
  DeviceStatistics getStatisticsByDeviceId(
      @PathVariable("deviceId") UUID deviceId,
      @RequestParam(value = "from", required = false) @DateTimeFormat(pattern = DATE_FORMAT) Date from,
      @RequestParam(value = "to", required = false) @DateTimeFormat(pattern = DATE_FORMAT) Date to) {
    if (from == null || to == null) {
      log.info("Requested statistics for device {}", deviceId);
      return service.getStatistics(deviceId);
    }
    log.info("Requested statistics for device {} between {} and {}", deviceId, from, to);
    return service.getStatistics(deviceId, from, to);
  }

}
