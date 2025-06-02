package com.example.weathertrack;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;

public class WeatherRepository {
    private Context context;
    private WeatherDao weatherDao;

    public WeatherRepository(Context context) {
        this.context = context;
        WeatherDatabase db = WeatherDatabase.getInstance(context);
        weatherDao = db.weatherDao();
    }

    public Weather getMockWeather() {
        try {
            // Read the JSON file from assets
            InputStream is = context.getAssets().open("weather_mock.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String json = new String(buffer, StandardCharsets.UTF_8); // safer charset

            // Parse as JSON array
            JSONArray jsonArray = new JSONArray(json);

            // Get today's index (0 = Monday, ..., 6 = Sunday)
            Calendar calendar = Calendar.getInstance();
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // Sunday = 1, Monday = 2, ...
            int index = (dayOfWeek + 5) % 7; // Adjust to 0-based index starting at Monday

            // Get today's object
            JSONObject obj = jsonArray.getJSONObject(index);

            int temp = obj.getInt("temperature");
            int hum = obj.getInt("humidity");
            String cond = obj.getString("condition");

            long timestamp = System.currentTimeMillis();

            // Save to DB
            WeatherEntity entity = new WeatherEntity(temp, hum, cond, timestamp);
            new Thread(() -> weatherDao.insert(entity)).start();

            return new Weather(temp, hum, cond);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
