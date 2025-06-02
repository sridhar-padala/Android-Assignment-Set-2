package com.example.weathertrack;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WeatherViewModel extends ViewModel {
    private MutableLiveData<Weather> weatherData = new MutableLiveData<>();
    private WeatherRepository repository;

    public void init(Context context) {
        if (repository == null) {
            repository = new WeatherRepository(context);
        }
    }

    public LiveData<Weather> getWeather() {
        return weatherData;
    }

    public void fetchWeather() {
        Weather weather = repository.getMockWeather();
        weatherData.setValue(weather);
    }
}

