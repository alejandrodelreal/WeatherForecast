package training.weather.client.metaweather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Component;
import training.weather.client.GetClient;
import training.weather.client.JSonConvertible;
import training.weather.client.metaweather.dto.ConsolidatedLocationDTO;
import training.weather.client.metaweather.dto.LocationDTO;

import java.io.IOException;
import java.util.List;

@Component("MetaWeatherClient")
public class MetaWeatherClient implements JSonConvertible, GetClient {
  private static final String META_WEATHER_BASE_URL = "https://www.metaweather.com/api/location/";
  private static final String SEARCH_QUERY_URL = META_WEATHER_BASE_URL + "search/?query=";

  public List<LocationDTO> getLocationQueryingCity(String city) throws IOException {
    String rawResponse = get(SEARCH_QUERY_URL + city);
    return toLocationList(rawResponse);
  }

  public ConsolidatedLocationDTO getLocationByCityId(int cityId) throws IOException {
    String rawResponse = get(META_WEATHER_BASE_URL + cityId);
    return toConsolidatedLocation(rawResponse);
  }

  private List<LocationDTO> toLocationList(String rawResponse) throws JsonProcessingException {
    return mapObject(rawResponse, new TypeReference<List<LocationDTO>>() {});
  }

  private ConsolidatedLocationDTO toConsolidatedLocation(String rawResponse) throws JsonProcessingException {
    return mapObject(rawResponse, ConsolidatedLocationDTO.class);
  }
}
