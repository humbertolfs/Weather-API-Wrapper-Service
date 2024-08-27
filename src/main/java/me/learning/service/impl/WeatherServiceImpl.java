package me.learning.service.impl;

import lombok.val;
import me.learning.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Object getCurrentWeather(String city) {
        val response = restTemplate
                .getForEntity(String.format("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/%s?unitGroup=metric&include=current&key=S7KNMTCQCP7UTQNPM5BJF56LL&contentType=json", city)
                        , Object.class);
        return response.getBody();
    }
}
