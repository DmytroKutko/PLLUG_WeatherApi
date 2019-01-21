package com.example.user.pllug_weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.pllug_weather.model.oneDay.WeatherData;
import com.example.user.pllug_weather.service.WeatherOneDayService;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    Button btnSubmit;
    TextView tvCity, tvId;
    EditText etCityName;
    WeatherOneDayService weatherService;

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
                weatherService.getOneDayWeather(new WeatherOneDayService.LoadData<WeatherData>() {
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
                }, etCityName.getText().toString());
            }
        });

    }

    private void initView() {
        btnSubmit = findViewById(R.id.btnSubmit);
        etCityName = findViewById(R.id.etCityName);
        tvCity = findViewById(R.id.tvCity);
        tvId = findViewById(R.id.tvId);
        weatherService = new WeatherOneDayService();
    }

    private void onDataUpdate(WeatherData data) {
        tvCity.setText("City name:\t" + data.getName());
        tvId.setText("Sunrise:\t" + data.getId());
    }
}
