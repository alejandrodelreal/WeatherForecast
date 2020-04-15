package training.weather.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
public class WeatherForecastService {
  private DateService dateService = new DateService();
  private WeatherService weatherService = new WeatherService();

  public String getCityWeather(String city, int days) {
    return getCityWeather(city, LocalDate.now().plusDays(days));
  }

  public String getCityWeather(String city, LocalDate datetime) {
    if (datetime == null) {
      datetime = LocalDate.now();
    }
    if (dateService.isBefore(datetime)) {
      Optional<Integer> cityId = weatherService.getWoeid(city);
      Optional<String> weatherStateName = weatherService.getWeatherStateName(cityId.orElse(-1), datetime);
      return weatherStateName.orElse("");
    }
    return "";
  }
}
