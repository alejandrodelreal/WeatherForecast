package training.weather.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import training.weather.client.metaweather.MetaWeatherClient;
import training.weather.client.metaweather.dto.ConsolidatedWeatherDTO;
import training.weather.client.metaweather.dto.LocationDTO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class WeatherService {
  private MetaWeatherClient client = new MetaWeatherClient();

  public Optional<Integer> getWoeid(String city) {
    try {
      List<LocationDTO> result = client.getLocationQueryingCity(city);
      if (CollectionUtils.isNotEmpty(result)) {
        return Optional.ofNullable(client.getLocationQueryingCity(city).get(0).getWoeid());
      }
      return Optional.empty();
    } catch (IOException e) {
      log.error("in getLocationQueryingCity", e);
      return Optional.empty();
    }
  }

  public Optional<String> getWeatherStateName(int city, LocalDate datetime) {
    try {
      List<ConsolidatedWeatherDTO> weatherList = client.getLocationByCityId(city).getConsolidatedWeather();
      Optional<ConsolidatedWeatherDTO> first = weatherList.stream()
        .filter(weather -> datetime.isEqual(weather.getApplicableDate()))
        .findFirst();
      return Optional.of(first.orElseGet(emptyConsolidatedWeather()).getWeatherStateName());
    } catch (IOException e) {
      log.error("in getLocationByCityId", e);
      return Optional.empty();
    }
  }

  private Supplier<ConsolidatedWeatherDTO> emptyConsolidatedWeather() {
    return () -> new ConsolidatedWeatherDTO("", null);
  }
}
