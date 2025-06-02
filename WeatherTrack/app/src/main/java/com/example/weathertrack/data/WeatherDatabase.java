package com.example.weathertrack.data;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {WeatherEntity.class}, version = 1, exportSchema = false)
public abstract class WeatherDatabase extends RoomDatabase {
    public abstract WeatherDao weatherDao();
    
    private static volatile WeatherDatabase INSTANCE;
    
    public static WeatherDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WeatherDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WeatherDatabase.class, "weather_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
