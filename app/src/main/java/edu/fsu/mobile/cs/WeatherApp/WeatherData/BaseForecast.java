/**
 * This class holds the data that is shared between CurrentForecast objects and ForecastInterval
 * objects. It is abstract and should NOT ever be instantiated. Its sole purpose is to save me from
 * witting the same variable declarations and get methods in the CurrentForecast and
 * ForecastInterval classes.
 */

package edu.fsu.mobile.cs.WeatherApp.WeatherData;

import org.json.JSONObject;

public abstract class BaseForecast {

    protected String description;
    protected double temp, temp_min, temp_max;
    protected double humidity;
    protected double windSpeed, windDirection;
    protected double pressure;

    public abstract void parseJSONData(JSONObject response);

    // GET METHODS
    public String getDescription() {
        return description;
    }

    public double getTemp() {
        return temp;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public double getPressure() {
        return pressure;
    }
}
