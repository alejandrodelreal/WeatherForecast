package training.weather.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateService {
  public LocalDate dateToLocalDateTime(Date datetime) {
    return Instant.ofEpochMilli(datetime.getTime())
      .atZone(ZoneId.systemDefault())
      .toLocalDate();
  }

  public boolean isBefore(LocalDate datetime) {
    return datetime.isBefore(LocalDate.now().plusDays(7));
  }
}
