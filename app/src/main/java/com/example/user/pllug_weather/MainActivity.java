package com.example.user.pllug_weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.pllug_weather.model.oneDay.WeatherData;
import com.example.user.pllug_weather.service.CurrentWeatherService;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    ImageButton btnSubmit;
    ImageView ivWeather;
    TextView tvCity, tvId, tvTemperature, tvClouds;
    EditText etCityName;
    CurrentWeatherService weatherService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInitialData();
    }

    private void setInitialData() {
        initView();
        initListener();
    }

    private void initListener() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                weatherService.getCurrentData(new CurrentWeatherService.LoadData<WeatherData>() {
                    @Override
                    public void onData(WeatherData data) {
                        Log.d(TAG, "onData: received");
                        onDataUpdate(data);
                        etCityName.setText("");
                    }

                    @Override
                    public void onFailure() {
                        Toast.makeText(MainActivity.this, "Fail to load data", Toast.LENGTH_SHORT).show();
                    }
                }, etCityName.getText().toString()); // Set city name !!!
            }
        });

    }

    private void initView() {
        btnSubmit = findViewById(R.id.btnSubmit);
        etCityName = findViewById(R.id.etCityName);
        tvCity = findViewById(R.id.tvCity);
        tvId = findViewById(R.id.tvId);
        tvTemperature = findViewById(R.id.tvTemperature);
        tvClouds = findViewById(R.id.tvClouds);
        weatherService = new CurrentWeatherService();
        ivWeather = findViewById(R.id.ivWeather);
    }

    private void onDataUpdate(WeatherData data) {
        tvCity.setText("City name:\t" + data.getName());
        tvId.setText("ID:\t" + data.getId());
        tvTemperature.setText("Temperature: \t" + temp(data) + "°C");
        tvClouds.setText("Weather:\t" + data.getWeather().get(0).getDescription());
        iconWeather(data);
    }

    private void iconWeather(WeatherData data) {
        switch (data.getWeather().get(0).getDescription()) {
            case "clear sky":
                ivWeather.setImageResource(R.drawable.sun);
                break;

            case "few clouds":
                ivWeather.setImageResource(R.drawable.few_clouds);
                break;

            case "scattered clouds":
                ivWeather.setImageResource(R.drawable.scattered_clouds);
                break;

            case "broken clouds":
                ivWeather.setImageResource(R.drawable.broken_clouds);
                break;

            case "overcast clouds":
                ivWeather.setImageResource(R.drawable.broken_clouds);
                break;

            case "shower rain":
                ivWeather.setImageResource(R.drawable.shower_rain);
                break;

            case "light intensity drizzle":
                ivWeather.setImageResource(R.drawable.shower_rain);
                break;

            case "drizzle":
                ivWeather.setImageResource(R.drawable.shower_rain);
                break;

            case "heavy intensity drizzle":
                ivWeather.setImageResource(R.drawable.shower_rain);
                break;

            case "drizzle rain":
                ivWeather.setImageResource(R.drawable.shower_rain);
                break;

            case "heavy intensity drizzle rain":
                ivWeather.setImageResource(R.drawable.shower_rain);
                break;

            case "light intensity drizzle rain":
                ivWeather.setImageResource(R.drawable.shower_rain);
                break;

            case "shower rain and drizzle":
                ivWeather.setImageResource(R.drawable.shower_rain);
                break;

            case "heavy shower rain and drizzle":
                ivWeather.setImageResource(R.drawable.shower_rain);
                break;

            case "shower drizzle":
                ivWeather.setImageResource(R.drawable.shower_rain);
                break;

            case "light intensity shower rain":
                ivWeather.setImageResource(R.drawable.shower_rain);
                break;

            case "heavy intensity shower rain":
                ivWeather.setImageResource(R.drawable.shower_rain);
                break;

            case "ragged shower rain":
                ivWeather.setImageResource(R.drawable.shower_rain);
                break;

            case "rain":
                ivWeather.setImageResource(R.drawable.rain);
                break;

            case "light rain":
                ivWeather.setImageResource(R.drawable.rain);
                break;

            case "moderate rain":
                ivWeather.setImageResource(R.drawable.rain);
                break;

            case "heavy intensity rain":
                ivWeather.setImageResource(R.drawable.rain);
                break;

            case "very heavy rain":
                ivWeather.setImageResource(R.drawable.rain);
                break;

            case "extreme rain":
                ivWeather.setImageResource(R.drawable.rain);
                break;

            case "thunderstorm":
                ivWeather.setImageResource(R.drawable.thunderstorm);
                break;

            case "thunderstorm with light rain":
                ivWeather.setImageResource(R.drawable.thunderstorm);
                break;

            case "thunderstorm with rain":
                ivWeather.setImageResource(R.drawable.thunderstorm);
                break;

            case "thunderstorm with heavy rain":
                ivWeather.setImageResource(R.drawable.thunderstorm);
                break;

            case "light thunderstorm":
                ivWeather.setImageResource(R.drawable.thunderstorm);
                break;

            case "heavy thunderstorm":
                ivWeather.setImageResource(R.drawable.thunderstorm);
                break;

            case "ragged thunderstorm":
                ivWeather.setImageResource(R.drawable.thunderstorm);
                break;

            case "thunderstorm with light drizzle":
                ivWeather.setImageResource(R.drawable.thunderstorm);
                break;

            case "thunderstorm with drizzle":
                ivWeather.setImageResource(R.drawable.thunderstorm);
                break;

            case "thunderstorm with heavy drizzle":
                ivWeather.setImageResource(R.drawable.thunderstorm);
                break;

            case "snow":
                ivWeather.setImageResource(R.drawable.snow);
                break;

            case "freezing rain":
                ivWeather.setImageResource(R.drawable.snow);
                break;

            case "light snow":
                ivWeather.setImageResource(R.drawable.snow);
                break;

            case "heavy snow":
                ivWeather.setImageResource(R.drawable.snow);
                break;

            case "sleet":
                ivWeather.setImageResource(R.drawable.snow);
                break;

            case "shower sleet":
                ivWeather.setImageResource(R.drawable.snow);
                break;

            case "light rain and snow":
                ivWeather.setImageResource(R.drawable.snow);
                break;

            case "rain and snow":
                ivWeather.setImageResource(R.drawable.snow);
                break;

            case "light shower snow":
                ivWeather.setImageResource(R.drawable.snow);
                break;

            case "shower snow":
                ivWeather.setImageResource(R.drawable.snow);
                break;

            case "heavy shower snow":
                ivWeather.setImageResource(R.drawable.snow);
                break;

            case "mist":
                ivWeather.setImageResource(R.drawable.mist);
                break;

            case "smoke":
                ivWeather.setImageResource(R.drawable.mist);
                break;

            case "haze":
                ivWeather.setImageResource(R.drawable.mist);
                break;

            case "sand, dust whirls":
                ivWeather.setImageResource(R.drawable.mist);
                break;

            case "fog":
                ivWeather.setImageResource(R.drawable.mist);
                break;

            case "sand":
                ivWeather.setImageResource(R.drawable.mist);
                break;

            case "dust":
                ivWeather.setImageResource(R.drawable.mist);
                break;

            case "volcanic ash":
                ivWeather.setImageResource(R.drawable.mist);
                break;

            case "squalls":
                ivWeather.setImageResource(R.drawable.mist);
                break;

            case "tornado":
                ivWeather.setImageResource(R.drawable.mist);
                break;

            default:
                Toast.makeText(this, "No icon " + data.getWeather().get(0).getDescription()
                        , Toast.LENGTH_SHORT).show();
        }
    }

    private int temp(WeatherData data) {
        double temp = Double.valueOf(data.getMain().getTemp()) - 273.15d;
        temp = Math.round(temp);
        int result = Integer.valueOf((int) temp);
        return result;
    }
}
