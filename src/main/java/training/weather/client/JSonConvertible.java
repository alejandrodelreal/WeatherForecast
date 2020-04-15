package training.weather.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface JSonConvertible {
  default <T> T mapObject(String rawResponse, Class<T> tClass) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(rawResponse, tClass);
  }

  default <T> T mapObject(String rawResponse, TypeReference<T> tClass) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(rawResponse, tClass);
  }
}
