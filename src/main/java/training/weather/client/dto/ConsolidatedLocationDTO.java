package training.weather.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ConsolidatedLocationDTO {
  @NotNull(message = "consolidated_weather is required field")
  @JsonProperty(value = "consolidated_weather", required = true)
  private List<ConsolidatedWeatherDTO> consolidatedWeather;
}
