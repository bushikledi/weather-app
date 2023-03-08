package com.project.weather.controller;

import com.project.weather.model.Location;
import com.project.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService forecastService;

    @GetMapping("/")
    public Location getWeatherData() {
        return forecastService.download();
    }
}
