package com.example.weathertrack;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class WeatherWorker extends Worker {

    public WeatherWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        WeatherRepository repo = new WeatherRepository(getApplicationContext());
        Weather weather = repo.getMockWeather();

        return weather != null ? Result.success() : Result.failure();
    }
}
