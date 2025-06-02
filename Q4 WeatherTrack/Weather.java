package com.example.weathertrack;

public class Weather {
    private int temperature;
    private int humidity;
    private String condition;

    public Weather(int temperature, int humidity, String condition) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.condition = condition;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public String getCondition() {
        return condition;
    }
}
