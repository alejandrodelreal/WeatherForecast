package training.weather.client.metaweather.dto;

import org.junit.Test;

import static nl.jqno.equalsverifier.EqualsVerifier.forClass;

public class ConsolidatedWeatherDTOTest {
  @Test
  public void equals_verifier_test() {
    forClass(ConsolidatedWeatherDTO.class).verify();
  }
}