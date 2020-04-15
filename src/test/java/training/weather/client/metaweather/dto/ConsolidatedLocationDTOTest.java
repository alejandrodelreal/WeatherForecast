package training.weather.client.metaweather.dto;

import org.junit.Test;

import static nl.jqno.equalsverifier.EqualsVerifier.forClass;

public class ConsolidatedLocationDTOTest {
  @Test
  public void equals_verifier_test() {
    forClass(ConsolidatedLocationDTO.class).verify();
  }
}