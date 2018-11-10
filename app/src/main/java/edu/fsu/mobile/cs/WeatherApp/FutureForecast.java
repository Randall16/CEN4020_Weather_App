package edu.fsu.mobile.cs.WeatherApp;

import edu.fsu.mobile.cs.WeatherApp.WeatherData.DayForecast;

public class FutureForecast {

    private static final int DAYS_SUPPORTED = 5;
    private DayForecast[] dayForecasts;

    public FutureForecast() {
        dayForecasts = new DayForecast[DAYS_SUPPORTED];
    }
}
