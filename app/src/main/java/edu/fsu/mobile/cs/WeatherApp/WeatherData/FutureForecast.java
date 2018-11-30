package edu.fsu.mobile.cs.WeatherApp.WeatherData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class FutureForecast {

    private static final int DAYS_SUPPORTED = 5;
    private static final int  TOTAL_INTERVALS = DAYS_SUPPORTED * 8;
    private ForecastInterval [] forecastIntervals;
    private String [] dailyDescriptions;
    private double [] dailyHighs;
    private double [] dailyLows;
    private double latitude, longitude;
    private int zipCode;
    private boolean locationUsed;

    public FutureForecast(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        locationUsed = true;
        init();
    }

    public FutureForecast(int zip) {
        locationUsed = false;
        zipCode = zip;
        init();
    }

    public void parseJSONData(JSONObject data) {
        JSONArray temp = null;
        try {
            temp = data.getJSONArray("list");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        for(int i = 0; i < TOTAL_INTERVALS; i++) {
            try {
                forecastIntervals[i].parseJSONData(temp.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        storeData();
    }

    private void init() {
        forecastIntervals = new ForecastInterval[TOTAL_INTERVALS];
        for(int i = 0; i < TOTAL_INTERVALS; i++)
            forecastIntervals[i] = new ForecastInterval();

        dailyHighs = new double[DAYS_SUPPORTED];
        dailyLows = new double[DAYS_SUPPORTED];
    }

    private void storeData() {

        for(int i = 0; i < DAYS_SUPPORTED; i++) {

            double curMin = forecastIntervals[i*8].temp_min;
            double curHi = forecastIntervals[i*8].temp_max;

            for(int j = 1; j < 8; j++) {
                if(curMin > forecastIntervals[(i*8) +j].temp_min)
                    curMin = forecastIntervals[(i*8) +j].temp_min;

                if(curHi < forecastIntervals[(i*8) +j].temp_max )
                    curHi = forecastIntervals[(i*8) +j].temp_max;
            }

            dailyHighs[i] = curHi;
            dailyLows[i] = curMin;
        }
    }

    public double getDayHigh(int day) {
        return dailyHighs[day];
    }

    public double getDayLow(int day) {
        return dailyLows[day];

    }

    public String getDescription(int days) {
        return forecastIntervals[0].getDescription();
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getZipCode() {
        return zipCode;
    }

    public boolean isLocationUsed() {
        return locationUsed;
    }
}
