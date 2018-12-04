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
            JSONObject desc = response;
            //response = response.getJSONObject("main");

            temp = response.getJSONObject("main").getDouble("temp");
            temp_min = response.getJSONObject("main").getDouble("temp_min");
            temp_max = response.getJSONObject("main").getDouble("temp_max");
            pressure = response.getJSONObject("main").getDouble("pressure");

            description = response.getJSONArray("weather").getJSONObject(0)
                    .getString("description");

        } catch (JSONException e) {

        }
    }


    public int getTime() {
        return time;
    }
}
