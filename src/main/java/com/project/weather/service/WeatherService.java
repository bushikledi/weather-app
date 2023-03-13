package com.project.weather.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.project.weather.model.Location;
import com.project.weather.model.Weather;
import com.project.weather.repository.LocationRepository;
import com.project.weather.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final LocationRepository locationRepository;
    private final WeatherRepository weatherRepository;
    private final RestTemplate restTemplate;
    private final String apiKey = System.getenv("API_KEY");


    public Location download() {
        Location location = new Location();
        List<Weather> weathers = new ArrayList<>();
        int dateCount = 0;
        String apiUrl = "https://api.openweathermap.org/data/2.5/forecast?q=London&appid=" + apiKey;

        JsonNode response = restTemplate.getForObject(apiUrl, JsonNode.class);

        JsonNode cityNode = response.get("city");
        location.setLocationId(cityNode.get("id").asInt());
        location.setCityName(cityNode.get("name").asText());
        location.setCountyName(cityNode.get("country").asText());

        JsonNode coordinationNode = cityNode.get("coord");
        location.setLongitude(coordinationNode.get("lon").asDouble());
        location.setLatitude(coordinationNode.get("lat").asDouble());

        JsonNode forecastNode = response.get("list");

        for (int i = 0; i < forecastNode.size(); i++) {

            Instant date = Instant.ofEpochSecond(forecastNode.get(i).get("dt").asLong());

            JsonNode mainNode = forecastNode.get(i).get("main");
            Double temperature = mainNode.get("temp").asDouble();
            Double pressure = mainNode.get("pressure").asDouble();
            Double humidity = mainNode.get("humidity").asDouble();

            JsonNode windNode = forecastNode.get(i).get("wind");
            Double direction = windNode.get("deg").asDouble();
            Double speed = windNode.get("speed").asDouble();
            Weather weather = Weather.builder()
                    .weatherId(dateCount++)
                    .date(date)
                    .humidity(humidity)
                    .pressure(pressure)
                    .temperature(temperature)
                    .windDirection(direction)
                    .windSpeed(speed)
                    .build();
            weatherRepository.save(weather);
            weathers.add(weather);
        }
        location.setWeathers(weathers);
        locationRepository.save(location);

        return location;
    }
}
