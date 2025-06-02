package com.example.weathertrack.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.*;
import com.example.weathertrack.R;
import com.example.weathertrack.viewmodel.WeatherViewModel;
import com.example.weathertrack.worker.WeatherWorker;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private WeatherViewModel viewModel;
    private WeatherAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        
        setupUI();
        setupRecyclerView();
        observeWeatherData();
        setupPeriodicWork();
    }
    
    private void setupUI() {
        Button refreshBtn = findViewById(R.id.btn_refresh);
        Button cleanupBtn = findViewById(R.id.btn_cleanup);
        
        refreshBtn.setOnClickListener(v -> {
            viewModel.fetchAndSaveWeather();
            Toast.makeText(this, "Fetching new weather data...", Toast.LENGTH_SHORT).show();
        });
        
        cleanupBtn.setOnClickListener(v -> {
            viewModel.cleanupOldWeather();
            Toast.makeText(this, "Cleaned up old weather data", Toast.LENGTH_SHORT).show();
        });
    }
    
    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.rv_weather_list);
        adapter = new WeatherAdapter();
        
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
    
    private void observeWeatherData() {
        viewModel.getLast7DaysWeather().observe(this, weatherEntities -> {
            if (weatherEntities != null) {
                adapter.submitList(weatherEntities);
                if (weatherEntities.isEmpty()) {
                    Toast.makeText(this, "No weather data available. Tap refresh to get started!", 
                                 Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    
    private void setupPeriodicWork() {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
                
        PeriodicWorkRequest weatherWork = new PeriodicWorkRequest.Builder(
                WeatherWorker.class, 6, TimeUnit.HOURS)
                .setConstraints(constraints)
                .build();
                
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
                "weatherSync", 
                ExistingPeriodicWorkPolicy.KEEP, 
                weatherWork);
    }
}
