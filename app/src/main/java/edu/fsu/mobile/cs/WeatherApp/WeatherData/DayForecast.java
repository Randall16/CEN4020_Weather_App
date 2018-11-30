package edu.fsu.mobile.cs.WeatherApp.WeatherData;

import org.json.JSONObject;

public class DayForecast {

    private ForecastInterval[] forecastIntervals;
    private double dayHigh, dayLow;

    public DayForecast() {
        forecastIntervals = new ForecastInterval[8];
    }

    public double getDayHigh() {
        return dayHigh;
    }

    public double getDayLow() {
        return dayLow;
    }

    public void parseJSONData(JSONObject data,int index) {

        for(int i = 0; i < 8; i++) {
            forecastIntervals[i].parseJSONData(data);
        }
    }
}
