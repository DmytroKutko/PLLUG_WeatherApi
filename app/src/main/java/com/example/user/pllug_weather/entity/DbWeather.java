package com.example.user.pllug_weather.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.provider.ContactsContract;

@Entity(tableName = "weather_table")
public class DbWeather {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String cityName;

    private Double temp;

    private String weather;


    public DbWeather() {
    }

    public DbWeather(String cityName, Double temp, String weather) {
        this.cityName = cityName;
        this.temp = temp;
        this.weather = weather;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}
