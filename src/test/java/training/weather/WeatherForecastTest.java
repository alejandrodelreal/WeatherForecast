package training.weather;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class WeatherForecastTest {
	private WeatherForecast tested;

	@Before
	public void init() {
		tested = new WeatherForecast();
	}

	@Test
	public void unfinished_test() {
		String forecast = tested.getCityWeather("Madrid", LocalDate.now().plusDays(1));
		System.out.println(forecast);
	}
}