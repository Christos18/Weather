package com.oocode;

import java.time.DayOfWeek;
import java.util.HashMap;

public class CachingForecaster implements Forecaster {
    private final Forecaster delegate;
    public static final HashMap<String, Forecast> CACHE = new HashMap<>();

    public CachingForecaster(Forecaster delegate) {
        this.delegate = delegate;
    }


    @Override
    public Forecast forecastFor(String place, DayOfWeek dayNumber) {
        String key = place + "-" + dayNumber;

        if (CACHE.containsKey(key)) {
            return CACHE.get(key);
        }

        Forecast forecast = delegate.forecastFor(place, dayNumber);
        CACHE.put(key, forecast);

        return forecast;
    }
}
