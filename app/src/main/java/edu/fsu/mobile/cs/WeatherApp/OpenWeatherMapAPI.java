package edu.fsu.mobile.cs.WeatherApp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class OpenWeatherMapAPI {

    private static RequestQueue requestQueue = null;

    private static final String APIKEY = "8a052996f128e0194ddd3059f32abd7f";
    private static String URL = "http://api.openweathermap.org/data/2.5/weather?lat=30.4383" +
            "&lon=-84.2807&appid=" + APIKEY;

    public static void retrieveCurrentForecast(Context context, final CurrentForecast currentForecast,
                                               final OnFetchCompleteListener mListener) {

        // if VolleyQueue is null create one with incoming context
        if(requestQueue == null)
            requestQueue = Volley.newRequestQueue(context);

        JsonRequest req = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {

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
}
