package training.weather.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
public class WeatherForecastService {
  private DateService dateService = new DateService();
  private WeatherService weatherService = new WeatherService();

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
