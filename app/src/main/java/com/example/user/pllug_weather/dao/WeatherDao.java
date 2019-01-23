package com.example.user.pllug_weather.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.user.pllug_weather.entity.DbWeather;

import java.util.List;

@Dao
public interface WeatherDao {

    @Insert
    void insert(DbWeather dbWeather);

    @Update
    void update(DbWeather dbWeather);

    @Query("SELECT * FROM weather_table")
    LiveData<List<DbWeather>> getAllDbWeather();

}
