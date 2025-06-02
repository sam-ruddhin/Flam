package com.example.weathertrack.repository;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import com.example.weathertrack.data.*;
import com.example.weathertrack.network.WeatherApi;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WeatherRepository {
    private final WeatherDao weatherDao;
    private final ExecutorService executor;

    public WeatherRepository(@NonNull WeatherDao weatherDao) {
        this.weatherDao = weatherDao;
        this.executor = Executors.newFixedThreadPool(2);
    }

    public void insertWeather(@NonNull WeatherEntity weather) {
        executor.execute(() -> weatherDao.insert(weather));
    }
    
    public void fetchAndSaveWeather() {
        executor.execute(() -> {
            WeatherEntity weather = WeatherApi.fetchMockWeather();
            weatherDao.insert(weather);
        });
    }

    public LiveData<List<WeatherEntity>> getLast7DaysWeather() {
        return weatherDao.getLast7Days();
    }

    public LiveData<WeatherEntity> getWeatherForDay(@NonNull String date) {
        return weatherDao.getWeatherForDay(date);
    }
    
    public void cleanupOldWeather() {
        executor.execute(() -> weatherDao.deleteOldWeather());
    }
}
