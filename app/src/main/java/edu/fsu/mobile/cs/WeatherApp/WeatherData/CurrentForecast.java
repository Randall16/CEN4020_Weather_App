/**
 * This class extends our BaseForecast class along with adding additional info like "visibility",
 * "sunrise" and "sunset". This data is exclusive to CurrentForecast and therefore can't be included
 * in the BaseForecast class.
 */

package edu.fsu.mobile.cs.WeatherApp.WeatherData;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import edu.fsu.mobile.cs.WeatherApp.WeatherData.BaseForecast;

public class CurrentForecast extends BaseForecast {

    private String city, country;
    private int zipCode;
    private double latitude, longitude;
    private boolean locationUsed;
    private int visibility;
    private int sunrise, sunset;    // stored as UNIX timestamps
    private char metric;

    // Constructor takes two doubles as parameter serving as the Latitude
    // and Longitude for the forecast.
    public CurrentForecast(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        locationUsed = true;
        metric = 'K';
    }

    public CurrentForecast(int zip) {
        this.zipCode = zip;
        locationUsed = false;
        metric = 'K';
    }

    // Method below extracts the JSON data that has retrieved from the API.
    @Override
    public void parseJSONData(JSONObject response) {



        // necessary try/catch when working with JSON data
        try {

            description = response.getJSONArray("weather").getJSONObject(0)
                    .getString("description");

            visibility = response.getInt("visibility");
            city = response.getString("name");

            windSpeed = response.getJSONObject("wind").getDouble("speed");
            windDirection = response.getJSONObject("wind").getDouble("deg");

            // country and sunrise/sunset are stored in nested "sys" object
            JSONObject sys = response.getJSONObject("sys");
            country = sys.getString("country");
            sunrise = sys.getInt("sunrise");
            sunset = sys.getInt("sunset");

            // rest of data is nested inside JSONObject "main"
            JSONObject main = response.getJSONObject("main");
            temp = main.getDouble("temp");
            temp_max = main.getDouble("temp_min");
            temp_min = main.getDouble("temp_max");
            humidity = main.getDouble("humidity");
            pressure = main.getDouble("pressure");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Log.v("inCF", temp + "");
        //Log.v("inCF", sunrise + "");

    }

    public void convertToFahrenheit() {
        if(metric == 'K') {
            // Using formula to convert from Kelvin to celsius
            temp = (temp - 273.15) * 9/5 + 32;
            temp_min = (temp_min - 273.15) * 9/5 + 32;
            temp_max = (temp_max - 273.15) * 9/5 + 32;
            metric = 'F';
        }
    }

    // GET METHODS
    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getVisibility() {
        return visibility;
    }

    public int getSunrise() {
        return sunrise;
    }

    public int getSunset() {
        return sunset;
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
