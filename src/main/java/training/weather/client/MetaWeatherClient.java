package training.weather.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import training.weather.client.dto.ConsolidatedLocationDTO;
import training.weather.client.dto.LocationDTO;

import java.io.IOException;
import java.util.List;

public class MetaWeatherClient {
  private static final String META_WEATHER_BASE_URL = "https://www.metaweather.com/api/location/";
  private static final String SEARCH_QUERY_URL = META_WEATHER_BASE_URL + "search/?query=";

  public List<LocationDTO> getLocationQueryingCity(String city) throws IOException {
    String rawResponse = executeGet(SEARCH_QUERY_URL + city);
    return toLocationList(rawResponse);
  }

  public ConsolidatedLocationDTO getLocationByCityId(int cityId) throws IOException {
    String rawResponse = executeGet(META_WEATHER_BASE_URL + cityId);
    return toConsolidatedLocation(rawResponse);
  }

  private String executeGet(String url) throws IOException {
    HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
    HttpRequest request = requestFactory
      .buildGetRequest(new GenericUrl(url));
    return request.execute().parseAsString();
  }

  private List<LocationDTO> toLocationList(String rawResponse) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(rawResponse, new TypeReference<List<LocationDTO>>() {});
  }

  private ConsolidatedLocationDTO toConsolidatedLocation(String rawResponse) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(rawResponse, ConsolidatedLocationDTO.class);
  }
}
