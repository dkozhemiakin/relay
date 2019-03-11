CREATE TABLE metrics
(
  device_id uuid,
  date      timestamp,
  value     double,
  PRIMARY KEY ((device_id), date)
);
