package com.example.weathertrack.worker;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.example.weathertrack.WeatherTrackApp;
import com.example.weathertrack.repository.WeatherRepository;

public class WeatherWorker extends Worker {
    private final WeatherRepository repository;
    
    public WeatherWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
        WeatherTrackApp app = (WeatherTrackApp) context.getApplicationContext();
        repository = app.appContainer.weatherRepository;
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            repository.fetchAndSaveWeather();
            return Result.success();
        } catch (Exception e) {
            return Result.retry();
        }
    }
}