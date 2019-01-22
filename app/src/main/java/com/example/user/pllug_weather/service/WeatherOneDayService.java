package com.example.user.pllug_weather.service;

import android.util.Log;

import com.example.user.pllug_weather.api.WeatherApi;
import com.example.user.pllug_weather.model.oneDay.Weather;
import com.example.user.pllug_weather.model.oneDay.WeatherData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherOneDayService {

    private static final String TAG = "WeatherOneDayService";

    private WeatherApi api;

    public WeatherOneDayService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api.API_LINK)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(WeatherApi.class);
    }

    public interface LoadData<T> {
        void onData(T data);

        void onFailure();
    }

//    public void getOneDayListWeather(final LoadData<List<Weather>> callback, final String name) {
//
//        api.getOneDayData(name, api.API_ID).enqueue(new Callback<WeatherData>() {
//            @Override
//            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
//                if (response.body() == null) {
//                    Log.d(TAG, "onResponse: fail");
//                    callback.onFailure();
//                    return;
//                }
//                callback.onData(response.body().getWeather()); // <-- MISTAKE!!!
//            }
//
//            @Override
//            public void onFailure(Call<WeatherData> call, Throwable t) {
//                Log.d(TAG, "onFailure: Fail load data");
//            }
//        });
//    }

    public void getOneDayData(final LoadData<WeatherData> callback, final String name) {
        api.getOneDayData(name, api.API_ID).enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                if (response.body() == null) {
                    Log.d(TAG, "onResponse: fail");
                    callback.onFailure();
                    return;
                }
                callback.onData(response.body());
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                Log.d(TAG, "onFailure: Fail load data WeatherData");
            }
        });
    }

}
