package training.weather.ws;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import training.weather.service.WeatherForecastService;

@RestController
@RequestMapping(
  path = "/forecast"
)
@Api(value = "WeatherForecastController",
  tags = {"WeatherForecast"})
public class WeatherForecastController {
  private WeatherForecastService weatherForecastService;

  @Autowired
  public WeatherForecastController() {
    weatherForecastService = new WeatherForecastService();
  }

  @ApiOperation(tags = {"WeatherForecast"}, value = "getCityWeather", notes = "Returns the forecasted weather of a city", response = String.class)
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "OK")
  })
  @GetMapping(value = "cityWeather")
  public ResponseEntity<String> getCityWeather(
    @RequestParam(value = "city", required = true)
    @ApiParam(value = "a city Name", example = "Madrid")
      String cityName,
    @RequestParam(value = "days", required = true)
    @ApiParam(value = "number of days from now", example = "1")
      int days) {
    return ResponseEntity.ok(weatherForecastService.getCityWeather(cityName, days));
  }
}
