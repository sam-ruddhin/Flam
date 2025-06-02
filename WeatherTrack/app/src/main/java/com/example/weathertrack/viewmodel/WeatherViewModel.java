package com.example.weathertrack.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.weathertrack.data.WeatherEntity;
import com.example.weathertrack.repository.WeatherRepository;
import java.util.List;

public class WeatherViewModel extends AndroidViewModel {
    private final WeatherRepository repository;
    private final LiveData<List<WeatherEntity>> last7DaysWeather;

    public WeatherViewModel(@NonNull Application application) {
        super(application);
        WeatherTrackApp app = (WeatherTrackApp) application;
        repository = app.appContainer.weatherRepository;
        last7DaysWeather = repository.getLast7DaysWeather();
    }

    public LiveData<List<WeatherEntity>> getLast7DaysWeather() {
        return last7DaysWeather;
    }

    public void fetchAndSaveWeather() {
        repository.fetchAndSaveWeather();
    }

    public LiveData<WeatherEntity> getWeatherForDay(@NonNull String date) {
        return repository.getWeatherForDay(date);
    }
    
    public void cleanupOldWeather() {
        repository.cleanupOldWeather();
    }
}
