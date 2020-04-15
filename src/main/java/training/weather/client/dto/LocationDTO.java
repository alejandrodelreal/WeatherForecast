package training.weather.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class LocationDTO {
  @NotNull(message = "woeid is required field")
  @JsonProperty(value = "woeid", required = true)
  private int woeid;
}
