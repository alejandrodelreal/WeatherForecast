package training.weather.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DateService {
  public boolean isBefore(LocalDate datetime) {
    return datetime.isBefore(LocalDate.now().plusDays(7));
  }
}
