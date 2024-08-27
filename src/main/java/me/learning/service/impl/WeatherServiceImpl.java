package me.learning.service.impl;

import me.learning.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private RedisTemplate<String, Map<String, Object>> redisTemplate;

    @Autowired
    private RestTemplate restTemplate;

    private static final String STRING_KEY_PREFIX = "redis:forecast:";

    @Override
    public Map<String, Object> getCurrentWeather(String city) {
        Map<String, Object> response = redisTemplate.opsForValue().get(STRING_KEY_PREFIX + city);
        if (response == null){
            response = restTemplate
                    .getForEntity(String.format("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/%s?unitGroup=metric&include=current&key=S7KNMTCQCP7UTQNPM5BJF56LL&contentType=json", city)
                            , Map.class).getBody();
            redisTemplate.opsForValue().set(STRING_KEY_PREFIX + city, response, 12, TimeUnit.HOURS);
        }
        return response;
    }
}
