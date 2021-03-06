package com.example.user.pllug_weather;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.pllug_weather.model.oneDay.WeatherData;
import com.example.user.pllug_weather.service.CurrentWeatherService;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    double longitude, latitude;
    FusedLocationProviderClient client;
    ImageButton btnSubmit;
    Button btnLocation;
    ImageView ivWeather;
    TextView tvCity, tvWindSpeed, tvTemperature, tvClouds, tvCoords;
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
        requestPermission();
    }

    private void initView() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnLocation = findViewById(R.id.btnLocation);
        etCityName = findViewById(R.id.etCityName);
        tvCity = findViewById(R.id.tvCity);
        tvWindSpeed = findViewById(R.id.tvWindSpeed);
        tvTemperature = findViewById(R.id.tvTemperature);
        tvClouds = findViewById(R.id.tvClouds);
        tvCoords = findViewById(R.id.tvCoords);
        weatherService = new CurrentWeatherService();
        ivWeather = findViewById(R.id.ivWeather);
        client = LocationServices.getFusedLocationProviderClient(this);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
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
                        tvCoords.setText("");
                    }

                    @Override
                    public void onFailure() {
                        Toast.makeText(MainActivity.this, "Fail to load data", Toast.LENGTH_SHORT).show();
                    }
                }, etCityName.getText().toString()); // Set city name !!!
            }
        });

        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(MainActivity.this,
                        ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                client.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();

                            final String lat = String.format("%.5f", latitude).replace(",", ".");
                            final String lon = String.format("%.5f", longitude).replace(",", ".");

                            weatherService.getCurrentDataByLocation(lat, lon,   // Set latitude and longitude
                                    new CurrentWeatherService.LoadData<WeatherData>() {
                                        @Override
                                        public void onData(WeatherData data) {
                                            Log.d(TAG, "onData: received");
                                            onDataUpdate(data);
                                            tvCoords.setText("Latitude: " + lat + "\n" + "Longitude: " + lon);
                                        }

                                        @Override
                                        public void onFailure() {
                                            Toast.makeText(MainActivity.this, "Fail to load data Coordinates", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    }
                });
            }
        });
    }

    private void onDataUpdate(WeatherData data) {
        tvCity.setText(data.getName());
        tvWindSpeed.setText(data.getWind().getSpeed() + " meter/sec");
        tvTemperature.setText(temp(data) + "°C");
        tvClouds.setText(data.getWeather().get(0).getDescription());
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
