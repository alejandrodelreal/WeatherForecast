package training.weather.service;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class WeatherForecastService {
  private DateService dateService;
  private WeatherService weatherService;

  @Autowired
  public WeatherForecastService(DateService dateService, WeatherService weatherService) {
    this.dateService = dateService;
    this.weatherService = weatherService;
  }

  public Optional<String> getCityWeather(@NonNull String city, @NonNull Integer days) {
    return getCityWeather(city, LocalDate.now().plusDays(days));
  }

  public Optional<String> getCityWeather(String city, LocalDate datetime) {
    if (datetime == null) {
      datetime = LocalDate.now();
    }
    if (dateService.isBefore(datetime)) {
      Optional<Integer> cityId = weatherService.getWoeid(city);
      return weatherService.getWeatherStateName(cityId.orElse(-1), datetime);
    }
    return Optional.empty();
  }
}
