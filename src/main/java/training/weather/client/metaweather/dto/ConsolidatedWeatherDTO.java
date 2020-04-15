package training.weather.client.metaweather.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public final class ConsolidatedWeatherDTO {
  private final String weatherStateName;
  private final LocalDate applicableDate;

  @JsonCreator
  public ConsolidatedWeatherDTO(
    @JsonProperty(value = "weather_state_name", required = true)
    @NotNull(message = "weather_state_name is required field")
    final String weatherStateName,

    @JsonProperty(value = "applicable_date", required = true)
    @NotNull(message = "applicable_date is required field")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    final LocalDate applicableDate) {
    this.weatherStateName = weatherStateName;
    this.applicableDate = applicableDate;
  }
}
