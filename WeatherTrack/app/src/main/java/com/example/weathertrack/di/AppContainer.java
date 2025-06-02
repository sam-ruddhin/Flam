package com.example.weathertrack.di;

import android.app.Application;
import com.example.weathertrack.data.WeatherDatabase;
import com.example.weathertrack.repository.WeatherRepository;

public class AppContainer {
    public final WeatherRepository weatherRepository;
    
    public AppContainer(Application application) {
        WeatherDatabase database = WeatherDatabase.getDatabase(application);
        weatherRepository = new WeatherRepository(database.weatherDao());
    }
}