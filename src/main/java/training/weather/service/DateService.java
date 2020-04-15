package training.weather.service;

import java.time.LocalDate;

public class DateService {
  public boolean isBefore(LocalDate datetime) {
    return datetime.isBefore(LocalDate.now().plusDays(7));
  }
}
