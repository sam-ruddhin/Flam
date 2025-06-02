package com.example.weathertrack.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WeatherEntity weather);

    @Query("SELECT * FROM weather ORDER BY date DESC LIMIT 7")
    LiveData<List<WeatherEntity>> getLast7Days();

    @Query("SELECT * FROM weather WHERE date = :day LIMIT 1")
    LiveData<WeatherEntity> getWeatherForDay(String day);

    @Query("DELETE FROM weather WHERE date < date('now', '-30 days')")
    void deleteOldWeather();
}