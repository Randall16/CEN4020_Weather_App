package edu.fsu.mobile.cs.WeatherApp;

public class CurrentForecastItems {

    private int WeatherIcon;
    private String Date;
    private String WeatherDescription;
    private String HighTemp;
    private String LowTemp;
    private String City;

    public CurrentForecastItems(int weatherIcon, String date, String weatherDescription, String highTemp, String lowTemp, String city) {
        WeatherIcon = weatherIcon;
        Date = date;
        WeatherDescription = weatherDescription;
        HighTemp = highTemp;
        LowTemp = lowTemp;
        City = city;
    }

    public int getWeatherIcon() { return WeatherIcon; }

    public void setWeatherIcon(int weatherIcon) { WeatherIcon = weatherIcon; }

    public String getDate() { return Date; }

    public void setDate(String date) { Date = date; }

    public String getWeatherDescription() { return WeatherDescription; }

    public void setWeatherDescription(String weatherDescription) { WeatherDescription = weatherDescription; }

    public String getHighTemp() { return HighTemp; }

    public void setHighTemp(String highTemp) { HighTemp = highTemp; }

    public String getLowTemp() { return LowTemp; }

    public void setLowTemp(String lowTemp) { LowTemp = lowTemp; }

    public String getCity() { return City; }

    public void setCity(String city) { City = city; }
}
