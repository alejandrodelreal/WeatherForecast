package training.weather.service;

import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(JMockit.class)
public class WeatherForecastTest {
	private WeatherForecastService tested;

	@Mocked
	private WeatherService weatherService;

	@Before
	public void init() {
		tested = new WeatherForecastService(new DateService() , weatherService);
	}

	@Test
	public void givenAnyValidDatetime_whenGetCityWeatherIsRaining_thenReturnsRaining() {
		new Expectations() {
			{
				weatherService.getWoeid(anyString);
				result = Optional.of(123);
			}
			{
				weatherService.getWeatherStateName(anyInt, (LocalDate) any);
				result = Optional.of("Raining");
			}
		};
		String result = tested.getCityWeather("Madrid", LocalDate.now().plusDays(1));
		assertEquals("Raining", result);
	}

	@Test
	public void givenAnyValidDatetime_whenGetCityWeatherReturnsError_thenReturnsEmptyString() {
		new Expectations() {
			{
				weatherService.getWoeid(anyString);
				result = Optional.of(123);
			}
		};
		String result = tested.getCityWeather("Madrid", LocalDate.now().plusDays(1));
		assertEquals("", result);
	}

	@Test
	public void givenDatetimeNull_whenGetCityWeather_thenReturnIsNotEmptyString() {
		new Expectations() {
			{
				weatherService.getWoeid(anyString);
				result = Optional.of(123);
			}
			{
				weatherService.getWeatherStateName(anyInt, (LocalDate) any);
				result = Optional.of("Nublado");
			}
		};
		String result = tested.getCityWeather("Madrid", null);
		assertNotEquals("", result);
	}
}