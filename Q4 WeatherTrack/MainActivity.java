package com.example.weathertrack;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private WeatherViewModel viewModel;
    private TextView tvTemp, tvHumidity, tvCondition;
    private Button btnRefresh, btnSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        PeriodicWorkRequest weatherRequest =
                new PeriodicWorkRequest.Builder(WeatherWorker.class, 6, TimeUnit.HOURS)
                        .build();

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
                "weather_sync",
                ExistingPeriodicWorkPolicy.KEEP,
                weatherRequest
        );


        tvTemp = findViewById(R.id.tvTemp);
        tvHumidity = findViewById(R.id.tvHumidity);
        tvCondition = findViewById(R.id.tvCondition);
        btnRefresh = findViewById(R.id.btnRefresh);
        btnSummary = findViewById(R.id.btnSummary);


        viewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        viewModel.init(this);


        viewModel.getWeather().observe(this, weather -> {
            if (weather != null) {
                tvTemp.setText("Temperature: " + weather.getTemperature() + "°C");
                tvHumidity.setText("Humidity: " + weather.getHumidity() + "%");
                tvCondition.setText("Condition: " + weather.getCondition());
            } else {
                Toast.makeText(this, "Error reading weather data", Toast.LENGTH_SHORT).show();
            }
        });


        btnRefresh.setOnClickListener(v -> viewModel.fetchWeather());

        btnSummary.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
            startActivity(intent);
        });



    }
}
