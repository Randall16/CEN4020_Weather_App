/**
 * The OpenWeatherMapAPI splits the future forecast into intervals of three hours. This class
 * represents one of those three hour intervals.
 */
package edu.fsu.mobile.cs.WeatherApp.WeatherData;

import org.json.JSONException;
import org.json.JSONObject;

import edu.fsu.mobile.cs.WeatherApp.WeatherData.BaseForecast;

public class ForecastInterval extends BaseForecast {

    private int time;

    @Override
    public void parseJSONData(JSONObject response) {

        try {

            time = response.getInt("dt");
            // Almost all the data is nested inside of "main" JSON object
            response = response.getJSONObject("main");

            temp = response.getDouble("temp");
            temp_min = response.getDouble("temp_min");
            temp_max = response.getDouble("temp_max");
            pressure = response.getDouble("pressure");

            // left off here!

        } catch (JSONException e) {

        }
    }


    public int getTime() {
        return time;
    }
}
