package training.weather;

import training.weather.service.DateService;
import training.weather.service.WeatherService;

import java.time.LocalDate;
import java.util.Optional;

public class WeatherForecast {
  private DateService dateService = new DateService();
  private WeatherService weatherService = new WeatherService();

  public String getCityWeather(String city, LocalDate datetime) {
    if (datetime == null) {
      datetime = LocalDate.now();
    }
    if (dateService.isBefore(datetime)) {
      Optional<Integer> cityId = weatherService.getWoeid(city);
      Optional<String> weatherStateName = weatherService.getWeatherStateName(cityId.get(), datetime);
      return weatherStateName.orElse("");
    }
    return "";
  }
}
