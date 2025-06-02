package com.example.weathertrack;

import android.app.Application;
import com.example.weathertrack.di.AppContainer;

public class WeatherTrackApp extends Application {
    public AppContainer appContainer;
    
    @Override
    public void onCreate() {
        super.onCreate();
        appContainer = new AppContainer(this);
    }
}