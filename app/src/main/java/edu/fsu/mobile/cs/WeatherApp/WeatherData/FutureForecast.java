package edu.fsu.mobile.cs.WeatherApp.WeatherData;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import edu.fsu.mobile.cs.WeatherApp.StringUtil;


public class FutureForecast {

    public static final int DAYS_SUPPORTED = 5;
    public static final int  TOTAL_INTERVALS = DAYS_SUPPORTED * 8;
    private ForecastInterval [] forecastIntervals;
    private String [] dailyDescriptions;
    private double [] dailyHighs;
    private double [] dailyLows;
    private double latitude, longitude;
    private int zipCode;
    private boolean locationUsed;
    private char metric;

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

    public void convertToFahrenheit() {
        if(metric == 'K') {
            for(int i = 0; i < DAYS_SUPPORTED; i++) {
                dailyHighs[i] = (dailyHighs[i] - 273.15) * 9/5 + 32;
                dailyLows[i] = (dailyLows[i] - 273.15) * 9/5 + 32;
            }
        }
    }

    private void init() {
        forecastIntervals = new ForecastInterval[TOTAL_INTERVALS];
        for(int i = 0; i < TOTAL_INTERVALS; i++)
            forecastIntervals[i] = new ForecastInterval();

        dailyHighs = new double[DAYS_SUPPORTED];
        dailyLows = new double[DAYS_SUPPORTED];
        dailyDescriptions = new String[DAYS_SUPPORTED];
        metric = 'K';
    }

    private void storeData() {

        for(int i = 0; i < DAYS_SUPPORTED; i++) {
            boolean useMap = true;
            String hold = null;

            double curMin = forecastIntervals[i*8].temp_min;
            double curHi = forecastIntervals[i*8].temp_max;

            HashMap<String, Integer> counter = new HashMap<>();

            for(int j = 1; j < 8; j++) {
                final int INDEX = (i*8) +j;
                if(curMin > forecastIntervals[INDEX].temp_min)
                    curMin = forecastIntervals[INDEX].temp_min;

                if(curHi < forecastIntervals[INDEX].temp_max )
                    curHi = forecastIntervals[INDEX].temp_max;

                Log.v("inFFF", i +" " +j);
                hold = forecastIntervals[INDEX].getDescription();
                if(hold == null)
                    break;
                if(hold.contains("rain") || hold.contains("thunderstorm") || hold.contains("snow")) {
                    useMap = false;
                    break;
                }

                if(counter.containsKey(forecastIntervals[INDEX].getDescription())) {
                    Integer t = counter.get(forecastIntervals[INDEX].getDescription());
                    t++;
                    counter.put(forecastIntervals[INDEX].getDescription(), t);
                }
                else {
                    counter.put(forecastIntervals[INDEX].getDescription(), 0);
                }
            }

            dailyHighs[i] = curHi;
            dailyLows[i] = curMin;

            // stuff below is for getting the description
            Iterator it = counter.entrySet().iterator();
            int maxValue = 0;
            Map.Entry highest = null;
            while(it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                if( (int) pair.getValue() > maxValue) {
                    maxValue = (int) pair.getValue();
                    highest = pair;
                }
            }

            if(useMap)
                hold = (String) highest.getKey();

            dailyDescriptions[i] = hold;


        }
    }

    public double getDayHigh(int day) {
        return dailyHighs[day];
    }

    public double getDayLow(int day) {
        return dailyLows[day];
    }

    public String getDescription(int days) {
        return dailyDescriptions[days];
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

    public char getMetric() {
        return metric;
    }
}
