package edu.fsu.mobile.cs.WeatherApp;

public class StringUtil {

    public static String toString(double deg) {
        return String.valueOf( (int) Math.round(deg) );
    }

}
