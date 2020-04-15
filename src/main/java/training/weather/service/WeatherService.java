package training.weather.service;

import training.weather.client.metaweather.MetaWeatherClient;
import training.weather.client.metaweather.dto.ConsolidatedWeatherDTO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class WeatherService {
  private MetaWeatherClient client = new MetaWeatherClient();

  public Optional<Integer> getWoeid(String city) {
    try {
      return Optional.ofNullable(client.getLocationQueryingCity(city).get(0).getWoeid());
    } catch (IOException e) {
      return Optional.empty();
    }
  }

  public Optional<String> getWeatherStateName(int city, LocalDate datetime) {
    try {
      List<ConsolidatedWeatherDTO> weatherList = client.getLocationByCityId(city).getConsolidatedWeather();
      Optional<ConsolidatedWeatherDTO> first = weatherList.stream()
        .filter(weather -> datetime.isEqual(weather.getApplicableDate()))
        .findFirst();
      return Optional.of(first.orElseGet(ConsolidatedWeatherDTO::new).getWeatherStateName());
    } catch (IOException e) {
      return Optional.empty();
    }
  }
}
