/**
 * This class is responsible for communicating with the OpenWeatherMap API. An important distinction
 * here that all of it's methods are, static meaning that you DO NOT need to instantiate this class
 * to call its methods.
 *
 */

package edu.fsu.mobile.cs.WeatherApp.WeatherData;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public final class OpenWeatherMapAPI {

    private static RequestQueue requestQueue = null;
    private static final String APIKEY = "8a052996f128e0194ddd3059f32abd7f";

    public static void retrieveCurrentForecast(Context context, final CurrentForecast currentForecast,
                                               final OnFetchCompleteListener mListener) {

        // if VolleyQueue is null create one with incoming context
        if(requestQueue == null)
            requestQueue = Volley.newRequestQueue(context);

        JsonRequest req = new JsonObjectRequest(Request.Method.GET, generateCurrentURL(currentForecast),
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                currentForecast.parseJSONData(response);
                mListener.onFetchComplete();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("tester", error.getMessage());
            }
        });

        // add request to VolleyQueue
        requestQueue.add(req);
    }

    public static void retrieveFutureForecast(Context context, final FutureForecast futureForecast,
                                              final OnFetchCompleteListener mListener) {

        if(requestQueue == null)
            requestQueue = Volley.newRequestQueue(context);

        JsonRequest req = new JsonObjectRequest(Request.Method.GET, generateFutureURL(futureForecast),
                null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        futureForecast.parseJSONData(response);
                        mListener.onFetchComplete();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("tester", error.getMessage());
            }
        });

        // add request to VolleyQueue
        requestQueue.add(req);
    }

    private static String generateCurrentURL(CurrentForecast currentForecast) {
        return "https://api.openweathermap.org/data/2.5/weather?lat=" + currentForecast.getLatitude() +
                "&lon=" + currentForecast.getLatitude() + "&appid=" + APIKEY;
    }

    private static String generateFutureURL(FutureForecast futureForecast) {
        return "https://api.openweathermap.org/data/2.5/forecast?lat=" + futureForecast.getLatitude() +
                "&lon=" + futureForecast.getLongitude() + "&appid=" + APIKEY;
    }

    private OpenWeatherMapAPI() {
        /**
         * Purposely making this single constructor private to prevent this class from being
         * instantiated because as mentioned earlier the methods are all static.
         */
    }
}
