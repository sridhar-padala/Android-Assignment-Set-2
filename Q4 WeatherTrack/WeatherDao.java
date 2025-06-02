package com.example.weathertrack;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WeatherDao {
    @Insert
    void insert(WeatherEntity weather);

    @Query("SELECT * FROM weather_table ORDER BY timestamp DESC LIMIT 1")
    WeatherEntity getLatest();

    @Query("SELECT * FROM weather_table WHERE timestamp >= :startTime")
    List<WeatherEntity> getLast7Days(long startTime);




}
