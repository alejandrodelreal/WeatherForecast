package training.weather.service;

import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import training.weather.client.metaweather.MetaWeatherClient;
import training.weather.client.metaweather.dto.ConsolidatedLocationDTO;
import training.weather.client.metaweather.dto.ConsolidatedWeatherDTO;
import training.weather.client.metaweather.dto.LocationDTO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JMockit.class)
public class WeatherServiceTest {
  private WeatherService tested;

  @Mocked
  private MetaWeatherClient metaWeatherClient;

  @Before
  public void init() {
    tested = new WeatherService(metaWeatherClient);
  }

  @Test
  public void givenAnyCity_whenGetWoeidAndClientReturnsZeroCities_thenReturnsNotPresent() throws IOException {
    new Expectations() {
      {
        metaWeatherClient.getLocationQueryingCity(anyString);
        result = null;
      }
    };

    Optional<Integer> anyCity = tested.getWoeid("anyCity");

    assertFalse(anyCity.isPresent());
  }

  @Test
  public void givenAnyCity_whenGetWoeidAndClientReturnsCity_thenReturnsAnId() throws IOException {
    new Expectations() {
      {
        metaWeatherClient.getLocationQueryingCity(anyString);
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setWoeid(123);
        result = locationDTO;
      }
    };

    Optional<Integer> anyCity = tested.getWoeid("anyCity");

    assertTrue(anyCity.isPresent());
    assertEquals(123, anyCity.get().intValue());
  }

  @Test
  public void givenAnyCityAndDate_whenGetWeatherStateNameAndClientLocatesCity_thenReturnsState() throws IOException {
    new Expectations() {
      {
        metaWeatherClient.getLocationByCityId(anyInt);
        ConsolidatedLocationDTO locationDTO = new ConsolidatedLocationDTO();
        locationDTO.setConsolidatedWeather(mockedWeatherList());
        result = locationDTO;
      }
    };

    Optional<String> anyWeatherStateName = tested.getWeatherStateName(123, LocalDate.now());

    assertTrue(anyWeatherStateName.isPresent());
    assertEquals("Cloudy", anyWeatherStateName.get());
  }

  @Test
  public void givenAnyCityAndDate_whenGetWeatherStateNameAndClientThrowsIOException_thenReturnsNotPresent() throws IOException {
    new Expectations() {
      {
        metaWeatherClient.getLocationByCityId(anyInt);
        result = new IOException();
      }
    };

    Optional<String> anyWeatherStateName = tested.getWeatherStateName(123, LocalDate.now());

    assertFalse(anyWeatherStateName.isPresent());
  }

  private List<ConsolidatedWeatherDTO> mockedWeatherList() {
    List<ConsolidatedWeatherDTO> result = new ArrayList<>();

    ConsolidatedWeatherDTO cwTomorrow = new ConsolidatedWeatherDTO();
    cwTomorrow.setApplicableDate(LocalDate.now().plusDays(1));
    cwTomorrow.setWeatherStateName("Raining");
    result.add(cwTomorrow);

    ConsolidatedWeatherDTO cwToday = new ConsolidatedWeatherDTO();
    cwToday.setApplicableDate(LocalDate.now());
    cwToday.setWeatherStateName("Cloudy");
    result.add(cwToday);

    ConsolidatedWeatherDTO cwYesterday = new ConsolidatedWeatherDTO();
    cwYesterday.setApplicableDate(LocalDate.now().minusDays(1));
    cwYesterday.setWeatherStateName("Sunny");
    result.add(cwYesterday);

    return result;
  }
}