package me.learning.controller;

import me.learning.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("forecast")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("{city}")
    public ResponseEntity<Object> getWeather(@PathVariable String city){
        return ResponseEntity.ok(weatherService.getCurrentWeather(city));
    }
}
