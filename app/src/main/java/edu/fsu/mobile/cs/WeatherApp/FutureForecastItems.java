package edu.fsu.mobile.cs.WeatherApp;

public class FutureForecastItems {
    private int weatherIcon;
    private String mDate;
    private String mWeather_descript;
    private String mTemp_hi;
    private String mTemp_lo;

    public FutureForecastItems(int weatherIcon, String mDate, String mWeather_descript, String mTemp_hi, String mTemp_lo) {
        this.weatherIcon = weatherIcon;
        this.mDate = mDate;
        this.mWeather_descript = mWeather_descript;
        this.mTemp_hi = mTemp_hi;
        this.mTemp_lo = mTemp_lo;
    }

    /* Getters */
    public int getWeatherIcon() {
        return weatherIcon;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmWeather_descript() {
        return mWeather_descript;
    }

    public String getmTemp_hi() {
        return mTemp_hi;
    }

    public String getmTemp_lo() {
        return mTemp_lo;
    }

    /* Setters */
    public void setWeatherIcon(int weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public void setmWeather_descript(String mWeather_descript) {
        this.mWeather_descript = mWeather_descript;
    }

    public void setmTemp_hi(String mTemp_hi) {
        this.mTemp_hi = mTemp_hi;
    }

    public void setmTemp_lo(String mTemp_lo) {
        this.mTemp_lo = mTemp_lo;
    }
}