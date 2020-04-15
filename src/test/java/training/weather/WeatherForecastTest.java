package training.weather;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WeatherForecastTest {
	private WeatherForecast tested;
	@Before
	public void init() {
		tested = new WeatherForecast();
	}

	@Test
	public void unfinished_test() throws IOException {
		String forecast = tested.getCityWeather("Madrid", LocalDate.now().plusDays(1));
		System.out.println(forecast);
	}
}