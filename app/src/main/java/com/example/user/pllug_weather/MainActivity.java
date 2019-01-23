package com.example.user.pllug_weather;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.pllug_weather.entity.DbWeather;
import com.example.user.pllug_weather.model.oneDay.WeatherData;
import com.example.user.pllug_weather.service.CurrentWeatherService;
import com.example.user.pllug_weather.viewModel.WeatherViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private WeatherViewModel weatherViewModel;

    ImageButton btnSubmit;
    TextView tvCity, tvId, tvTemperature, tvClouds, tvTest;
    EditText etCityName;
    CurrentWeatherService weatherService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherViewModel = new ViewModelProviders().of(this).get(WeatherViewModel.class);
        weatherViewModel.getAllDbWeather().observe(this, new Observer<List<DbWeather>>() {
            @Override
            public void onChanged(@Nullable List<DbWeather> dbWeathers) {
                Toast.makeText(MainActivity.this, "Checked", Toast.LENGTH_SHORT).show();
                if (dbWeathers.size() != 0) {
                    tvTest.setText(dbWeathers.get(0).getCityName() + " " +
                            dbWeathers.get(0).getWeather() + " " +
                            dbWeathers.get(0).getTemp());
                }
            }
        });
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
        tvTest = findViewById(R.id.tvTest);
        weatherService = new CurrentWeatherService();
    }

    private void onDataUpdate(WeatherData data) {
        tvCity.setText("City name:\t" + data.getName());
        tvId.setText("ID:\t" + data.getId());
        tvTemperature.setText("Temperature: \t" + temp(data) + "°C");
        tvClouds.setText("Weather:\t" + data.getWeather().get(0).getDescription());
    }

    private int temp(WeatherData data) {
        double temp = Double.valueOf(data.getMain().getTemp()) - 273.15d;
        temp = Math.round(temp);
        int result = Integer.valueOf((int) temp);
        return result;
    }
}
