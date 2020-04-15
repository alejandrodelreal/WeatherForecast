package training.weather.client.metaweather.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public final class LocationDTO {
  private final int woeid;

  @JsonCreator
  public LocationDTO(
    @NotNull(message = "woeid is required field")
    @JsonProperty(value = "woeid", required = true)
      int woeid) {
    this.woeid = woeid;
  }
}
