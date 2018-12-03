package edu.fsu.mobile.cs.WeatherApp;

import java.util.HashMap;

public final class ImageUtil {
    private static final HashMap<String, Integer> IMAGE_MAP;

    static {
        IMAGE_MAP = new HashMap<>();
        IMAGE_MAP.put("thunderstorm with light rain", R.drawable.clouds_with_lighting_littlerain);
        IMAGE_MAP.put("thunderstorm with rain", R.drawable.clouds_with_lighting_littlerain);
        IMAGE_MAP.put("thunderstorm with heavy rain", R.drawable.clouds_with_lighting_rain);
        IMAGE_MAP.put("light thunderstorm", R.drawable.clouds_with_lighting);
        IMAGE_MAP.put("thunderstorm", R.drawable.clouds_with_2lighting);
        IMAGE_MAP.put("heavy thunderstorm", R.drawable.clouds_with_2lighting);
        IMAGE_MAP.put("ragged thunderstorm", R.drawable.clouds_with_2lighting);
        IMAGE_MAP.put("thunderstorm with light drizzle", R.drawable.clouds_with_lighting_littlerain);
        IMAGE_MAP.put("thunderstorm with drizzle", R.drawable.clouds_with_lighting_rain);
        IMAGE_MAP.put("thunderstorm with heavy drizzle", R.drawable.clouds_with_lighting_rain);


        IMAGE_MAP.put("light intensity drizzle", R.drawable.clouds_with_littlerain);
        IMAGE_MAP.put("drizzle", R.drawable.clouds_with_littlerain);
        IMAGE_MAP.put("heavy intensity drizzle", R.drawable.clouds_with_rain);
        IMAGE_MAP.put("light intensity drizzle rain", R.drawable.clouds_with_littlerain);
        IMAGE_MAP.put("drizzle rain", R.drawable.clouds_with_littlerain);
        IMAGE_MAP.put("heavy intensity drizzle rain", R.drawable.clouds_with_rain);
        IMAGE_MAP.put("shower rain and drizzle", R.drawable.clouds_with_littlerain);
        IMAGE_MAP.put("heavy shower rain and drizzle", R.drawable.clouds_with_rain);
        IMAGE_MAP.put("shower drizzle", R.drawable.clouds_with_rain);


        IMAGE_MAP.put("light rain", R.drawable.clouds_with_littlerain);
        IMAGE_MAP.put("moderate rain", R.drawable.clouds_with_littlerain);
        IMAGE_MAP.put("heavy intensity rain", R.drawable.clouds_with_rain);
        IMAGE_MAP.put("very heavy rain", R.drawable.clouds_with_rain);
        IMAGE_MAP.put("extreme rain", R.drawable.clouds_with_rain);
        IMAGE_MAP.put("light intensity shower rain", R.drawable.clouds_with_littlerain);
        IMAGE_MAP.put("shower rain", R.drawable.clouds_with_littlerain);
        IMAGE_MAP.put("heavy intensity shower rain", R.drawable.clouds_with_rain);
        IMAGE_MAP.put("ragged shower rain", R.drawable.clouds_with_rain);


        IMAGE_MAP.put("clear sky", R.drawable.sun);
        IMAGE_MAP.put("few clouds", R.drawable.clouds);
        IMAGE_MAP.put("scattered clouds", R.drawable.clouds);
        IMAGE_MAP.put("broken clouds", R.drawable.clouds);
        IMAGE_MAP.put("overcast clouds", R.drawable.clouds);




    }

    public static int getImage(String description) {
        return IMAGE_MAP.get(description);
    }
}
