package com.example.user.pllug_weather.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.user.pllug_weather.dao.WeatherDao;
import com.example.user.pllug_weather.db.WeatherDatabase;
import com.example.user.pllug_weather.entity.DbWeather;

import java.util.List;

public class WeatherRepository {

    private WeatherDao weatherDao;

    private LiveData<List<DbWeather>> allDbWeather;

    public WeatherRepository(Application application) {
        WeatherDatabase database = WeatherDatabase.getInstance(application);
        weatherDao = database.weatherDao();
        allDbWeather = weatherDao.getAllDbWeather();
    }

    public void insert(DbWeather weather) {
        new InsertDbWeatherAsyncTask(weatherDao).execute(weather);
    }

    public void update(DbWeather weather) {
        new UpdateDbWeatherAsyncTask(weatherDao).execute(weather);
    }

    public LiveData<List<DbWeather>> getAllDbWeather() {
        return allDbWeather;
    }

    private static class InsertDbWeatherAsyncTask extends AsyncTask<DbWeather, Void, Void> {

        private WeatherDao weatherDao;

        private InsertDbWeatherAsyncTask(WeatherDao weatherDao) {
            this.weatherDao = weatherDao;
        }

        @Override
        protected Void doInBackground(DbWeather... dbWeathers) {
            weatherDao.insert(dbWeathers[0]);
            return null;
        }
    }

    private static class UpdateDbWeatherAsyncTask extends AsyncTask<DbWeather, Void, Void> {

        private WeatherDao weatherDao;

        private UpdateDbWeatherAsyncTask(WeatherDao weatherDao) {
            this.weatherDao = weatherDao;
        }

        @Override
        protected Void doInBackground(DbWeather... dbWeathers) {
            weatherDao.update(dbWeathers[0]);
            return null;
        }
    }
}
