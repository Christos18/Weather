package com.oocode;

import java.time.DayOfWeek;

public class CachingForecaster implements Forecaster {
    private final Forecaster delegate;

    public CachingForecaster(Forecaster delegate) {
        this.delegate = delegate;
    }


    @Override
    public Forecast forecastFor(String place, DayOfWeek dayNumber) {
        return delegate.forecastFor(place, dayNumber);
    }
}
