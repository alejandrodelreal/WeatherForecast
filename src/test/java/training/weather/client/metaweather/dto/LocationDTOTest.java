package training.weather.client.metaweather.dto;

import org.junit.Test;

import static nl.jqno.equalsverifier.EqualsVerifier.forClass;

public class LocationDTOTest {
  @Test
  public void equals_verifier_test() {
    forClass(LocationDTO.class).verify();
  }
}