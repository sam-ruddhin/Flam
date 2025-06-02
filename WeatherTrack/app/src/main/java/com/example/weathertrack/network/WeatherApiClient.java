package com.example.weathertrack.network;

import com.example.weathertrack.data.WeatherEntity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class WeatherApi {
    private static final String[] CONDITIONS = {"Sunny", "Cloudy", "Rainy", "Stormy", "Foggy", "Snowy"};
    private static final String[] DESCRIPTIONS = {
        "Clear skies with bright sunshine",
        "Partly cloudy with occasional sun",
        "Light rain showers expected",
        "Heavy thunderstorms with strong winds",
        "Dense fog reducing visibility",
        "Snow flurries throughout the day"
    };
    
    private static final SimpleDateFormat DATE_FORMAT = 
        new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    
    public static WeatherEntity fetchMockWeather() {
        Random random = new Random();
        
        int conditionIndex = random.nextInt(CONDITIONS.length);
        String condition = CONDITIONS[conditionIndex];
        String description = DESCRIPTIONS[conditionIndex];
        String date = DATE_FORMAT.format(new Date());
        
        double temperature;
        int humidity;
        
        // Temperature varies by condition
        switch (condition) {
            case "Sunny":
                temperature = 25 + random.nextDouble() * 10;
                humidity = 40 + random.nextInt(20);
                break;
            case "Rainy":
                temperature = 15 + random.nextDouble() * 10;
                humidity = 70 + random.nextInt(25);
                break;
            case "Snowy":
                temperature = -5 + random.nextDouble() * 10;
                humidity = 60 + random.nextInt(30);
                break;
            case "Foggy":
                temperature = 10 + random.nextDouble() * 15;
                humidity = 80 + random.nextInt(15);
                break;
            default: // Cloudy
                temperature = 18 + random.nextDouble() * 12;
                humidity = 50 + random.nextInt(30);
        }
        
        return new WeatherEntity(date, temperature, humidity, condition, description);
    }
}