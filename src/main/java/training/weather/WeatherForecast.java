package training.weather;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherForecast {

	public String getCityWeather(String city, Date datetime) throws IOException {
		return getCityWeather(city, dateToLocalDateTime(datetime));
	}

	private LocalDate dateToLocalDateTime(Date datetime) {
		return Instant.ofEpochMilli(datetime.getTime())
			.atZone(ZoneId.systemDefault())
			.toLocalDate();
	}

	public String getCityWeather(String city, LocalDate datetime) throws IOException {
		if (datetime == null) {
			datetime = LocalDate.now();
		}
		if (isBefore(datetime)) {
			HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
			HttpRequest request = requestFactory
				.buildGetRequest(new GenericUrl("https://www.metaweather.com/api/location/search/?query=" + city));
			String rawResponse = request.execute().parseAsString();
			JSONArray jsonArray = new JSONArray(rawResponse);
			String cityId = jsonArray.getJSONObject(0).get("woeid").toString();
			requestFactory = new NetHttpTransport().createRequestFactory();
			request = requestFactory
				.buildGetRequest(new GenericUrl("https://www.metaweather.com/api/location/" + cityId));
			rawResponse = request.execute().parseAsString();
			JSONArray results = new JSONObject(rawResponse).getJSONArray("consolidated_weather");
			for (int i = 0; i < results.length(); i++) {
				if (DateTimeFormatter.ofPattern("yyyy-MM-dd").format(datetime)
					.equals(results.getJSONObject(i).get("applicable_date").toString())) {
					return results.getJSONObject(i).get("weather_state_name").toString();
				}
			}
		}
		return "";
	}

	protected boolean isBefore(LocalDate datetime) {
		return datetime.isBefore(LocalDate.now().plusDays(7));
	}
}
