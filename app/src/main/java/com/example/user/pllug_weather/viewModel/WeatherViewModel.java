package com.example.user.pllug_weather.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.user.pllug_weather.entity.DbWeather;
import com.example.user.pllug_weather.repository.WeatherRepository;

import java.util.List;

public class WeatherViewModel extends AndroidViewModel {

    private WeatherRepository repository;

    private LiveData<List<DbWeather>> allDbWeather;


    public WeatherViewModel(@NonNull Application application) {
        super(application);
        repository = new WeatherRepository(application);
        allDbWeather = repository.getAllDbWeather();
    }

    public void insert(DbWeather weather){
        repository.insert(weather);
    }

    public void update(DbWeather weather){
        repository.update(weather);
    }

    public LiveData<List<DbWeather>> getAllDbWeather(){
        return allDbWeather;
    }

}
