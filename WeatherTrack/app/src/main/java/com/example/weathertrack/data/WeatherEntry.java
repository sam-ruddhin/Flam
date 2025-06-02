package com.example.weathertrack.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Objects;

@Entity(tableName = "weather")
public class WeatherEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    
    @NonNull
    public String date;
    public double temperature;
    public int humidity;
    
    @NonNull
    public String condition;
    
    @NonNull
    public String description;

    public WeatherEntity(@NonNull String date, double temperature, int humidity, 
                        @NonNull String condition, @NonNull String description) {
        this.date = date;
        this.temperature = temperature;
        this.humidity = humidity;
        this.condition = condition;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherEntity that = (WeatherEntity) o;
        return id == that.id && 
               Double.compare(that.temperature, temperature) == 0 && 
               humidity == that.humidity && 
               date.equals(that.date) && 
               condition.equals(that.condition) && 
               description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, temperature, humidity, condition, description);
    }
}