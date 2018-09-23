/**
 * This class extends our BaseForecast class along with adding additional info like "visibility",
 * "sunrise" and "sunset". This data is exclusive to CurrentForecast and therefore can't be included
 * in the BaseForecast class.
 */

package edu.fsu.mobile.cs.WeatherApp;

import org.json.JSONException;
import org.json.JSONObject;

public class CurrentForecast extends BaseForecast {

    private String city, country;
    private double latitude, longitude;
    private int visibility;
    private int sunrise, sunset;    // stored as UNIX timestamps

    // Constructor takes two doubles as parameter serving as the Latitude
    // and Longitude for the forecast.
    public CurrentForecast(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Method below extracts the JSON data that has retrieved from the API.
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
}
