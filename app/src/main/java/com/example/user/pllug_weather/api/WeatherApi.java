package com.example.user.pllug_weather.api;

import com.example.user.pllug_weather.model.oneDay.OpenWeatherOneDay;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    String API_LINK = "https://api.openweathermap.org/";
    String API_KEY = "4a801829d4feb9cdaf20e55bef72cf94";

    @GET("data/2.5/weather")
    Call<OpenWeatherOneDay> getOneDayData(@Query("q") String city,
                                          @Query("key") String key);
}
