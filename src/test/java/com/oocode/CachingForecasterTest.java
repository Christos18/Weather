package com.oocode;

import org.junit.jupiter.api.Test;

import static java.time.DayOfWeek.FRIDAY;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CachingForecasterTest {
    @Test
    public void delegatesForecastingForNewForecast() {
        var delegate = mock(Forecaster.class);
        Forecaster.Forecast expectedForecast = new Forecaster.Forecast(1, 2, "cold");
        given(delegate.forecastFor("Oxford", FRIDAY)).willReturn(expectedForecast);

        var underTest = new CachingForecaster(delegate);

        var forecast = underTest.forecastFor("Oxford", FRIDAY);

        verify(delegate).forecastFor("Oxford", FRIDAY); // This is arguable
        assertThat(forecast, equalTo(expectedForecast));
    }

    @Test
    public void checksCacheForExistingForecast() {
        var delegate = mock(Forecaster.class);
        var underTest = new CachingForecaster(delegate);
        var cachedForecast = CachingForecaster.CACHE.put("Oxford-Friday", new Forecaster.Forecast(1, 2, "Cold"));

        assertThat(underTest.forecastFor("Oxford", FRIDAY), equalTo(cachedForecast));
    }
}
