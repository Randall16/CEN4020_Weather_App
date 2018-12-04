package edu.fsu.mobile.cs.WeatherApp;

import java.util.HashMap;

public final class ImageUtil {
    private static final HashMap<String, Integer> IMAGE_MAP;

    static {
        IMAGE_MAP = new HashMap<>();

        // Thunderstorms
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

        // Drizzle
        IMAGE_MAP.put("light intensity drizzle", R.drawable.clouds_with_littlerain);
        IMAGE_MAP.put("drizzle", R.drawable.clouds_with_littlerain);
        IMAGE_MAP.put("heavy intensity drizzle", R.drawable.clouds_with_rain);
        IMAGE_MAP.put("light intensity drizzle rain", R.drawable.clouds_with_littlerain);
        IMAGE_MAP.put("drizzle rain", R.drawable.clouds_with_littlerain);
        IMAGE_MAP.put("heavy intensity drizzle rain", R.drawable.clouds_with_rain);
        IMAGE_MAP.put("shower rain and drizzle", R.drawable.clouds_with_littlerain);
        IMAGE_MAP.put("heavy shower rain and drizzle", R.drawable.clouds_with_rain);
        IMAGE_MAP.put("shower drizzle", R.drawable.clouds_with_rain);

        // Rain
        IMAGE_MAP.put("light rain", R.drawable.clouds_with_littlerain);
        IMAGE_MAP.put("moderate rain", R.drawable.clouds_with_littlerain);
        IMAGE_MAP.put("heavy intensity rain", R.drawable.clouds_with_rain);
        IMAGE_MAP.put("very heavy rain", R.drawable.clouds_with_rain);
        IMAGE_MAP.put("extreme rain", R.drawable.clouds_with_rain);
        IMAGE_MAP.put("light intensity shower rain", R.drawable.clouds_with_littlerain);
        IMAGE_MAP.put("shower rain", R.drawable.clouds_with_littlerain);
        IMAGE_MAP.put("heavy intensity shower rain", R.drawable.clouds_with_rain);
        IMAGE_MAP.put("ragged shower rain", R.drawable.clouds_with_rain);

        // Snow
        IMAGE_MAP.put("light snow", R.drawable.clouds_with_littlesnow);
        IMAGE_MAP.put("snow", R.drawable.clouds_with_littlesnow);
        IMAGE_MAP.put("heavy snow", R.drawable.clouds_with_snow);
        IMAGE_MAP.put("sleet", R.drawable.clouds_with_snow);
        IMAGE_MAP.put("shower sleet", R.drawable.clouds_with_snow);
        IMAGE_MAP.put("light rain and snow", R.drawable.sun_rain_snow);
        IMAGE_MAP.put("rain and snow", R.drawable.sun_rain_snow);
        IMAGE_MAP.put("light shower snow", R.drawable.clouds_with_littlesnow);
        IMAGE_MAP.put("shower snow", R.drawable.clouds_with_littlesnow);
        IMAGE_MAP.put("heavy shower snow", R.drawable.clouds_with_littlesnow);

        // Random stuff
        IMAGE_MAP.put("mist", R.drawable.sun_haze);
        IMAGE_MAP.put("smoke", R.drawable.sun_haze);
        IMAGE_MAP.put("haze", R.drawable.sun_haze);
        IMAGE_MAP.put("fog", R.drawable.sun_haze);
        IMAGE_MAP.put("sand", R.drawable.sun_haze);
        IMAGE_MAP.put("dust", R.drawable.sun_haze);
        IMAGE_MAP.put("volcanic ash", R.drawable.sun_haze);
        IMAGE_MAP.put("squalls", R.drawable.sun_windy);
        IMAGE_MAP.put("tornado", R.drawable.sun_windy);

        // Clouds
        IMAGE_MAP.put("clear sky", R.drawable.sun);
        IMAGE_MAP.put("few clouds", R.drawable.sun_with_1cloud);
        IMAGE_MAP.put("scattered clouds", R.drawable.clouds);
        IMAGE_MAP.put("broken clouds", R.drawable.clouds);
        IMAGE_MAP.put("overcast clouds", R.drawable.clouds);

    }

    public static int getImage(String description) {
        if (IMAGE_MAP.containsKey(description))
            return IMAGE_MAP.get(description);
        else
            return R.drawable.sun;      // default image if not found
    }

    private ImageUtil() {
        // Purposely left blank constructor this class is just to hold static methods
    }
}
