package com.example.weathertrack;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class SummaryActivity extends AppCompatActivity {

    private TextView tvSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        tvSummary = findViewById(R.id.tvSummary);

        WeatherDao dao = WeatherDatabase.getInstance(this).weatherDao();

        new Thread(() -> {
            long now = System.currentTimeMillis();
            long sevenDaysAgo = now - (6L * 24 * 60 * 60 * 1000); // 7 days incl. today

            List<WeatherEntity> entries = dao.getLast7Days(sevenDaysAgo);

            // Group latest record per day
            Map<String, WeatherEntity> dailyMap = new LinkedHashMap<>();
            SimpleDateFormat keyFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

            for (WeatherEntity e : entries) {
                String key = keyFormat.format(new Date(e.timestamp));
                // Only keep the first (latest) entry for the day
                if (!dailyMap.containsKey(key)) {
                    dailyMap.put(key, e);
                }
            }

            StringBuilder summary = new StringBuilder();
            summary.append("City: Vijayawada\n\n");

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(now);

            // Go back 6 days and loop through each day up to today
            for (int i = 6; i >= 0; i--) {
                calendar.setTimeInMillis(now);
                calendar.add(Calendar.DAY_OF_YEAR, -i);
                String dateKey = keyFormat.format(calendar.getTime());
                WeatherEntity e = dailyMap.get(dateKey);

                String day = new SimpleDateFormat("EEEE", Locale.getDefault()).format(calendar.getTime());

                if (e != null) {
                    summary.append(dateKey)
                            .append(" - ")
                            .append(day)
                            .append(" - ")
                            .append(e.condition).append(", ")
                            .append(e.temperature).append("°C, ")
                            .append(e.humidity).append("% humidity\n");
                } else {
                    summary.append(dateKey)
                            .append(" - ")
                            .append(day)
                            .append(" - No data\n");
                }
            }

            runOnUiThread(() -> tvSummary.setText(summary.toString()));

        }).start();
    }
}

