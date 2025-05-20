package net.engineeringabhishek.journalApp.service;

import net.engineeringabhishek.journalApp.Constants.Placeholders;
import net.engineeringabhishek.journalApp.api.response.WeatherResponse;
import net.engineeringabhishek.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class WeatherService {


    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    AppCache appCache;

    @Autowired
    private RedisService redisService;

    @Autowired
    RestTemplate restTemplate;

    public WeatherResponse getWeather(String city) {
        WeatherResponse weatherResponse = redisService.get("weather_of_"+city, WeatherResponse.class);
        if(weatherResponse != null) {
            return weatherResponse;
        }
        String finalApi = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.API_KEY, apiKey).replace(Placeholders.CITY, city);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        if(body != null) {
            redisService.set("weather_of_"+ city, body, 300L);
        }
        return body;
    }
}
