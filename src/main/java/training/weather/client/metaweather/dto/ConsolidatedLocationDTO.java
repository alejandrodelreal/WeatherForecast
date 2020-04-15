package training.weather.client.metaweather.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public final class ConsolidatedLocationDTO {
  private final List<ConsolidatedWeatherDTO> consolidatedWeather;

  @JsonCreator
  public ConsolidatedLocationDTO(
    @NotNull(message = "consolidated_weather is required field")
    @JsonProperty(value = "consolidated_weather", required = true)
    List<ConsolidatedWeatherDTO> consolidatedWeather) {
    this.consolidatedWeather = consolidatedWeather;
  }
}
