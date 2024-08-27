package me.learning.service;

import java.util.Map;

public interface WeatherService {

    Map<String, Object> getCurrentWeather(String city);
}
