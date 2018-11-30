/**
 * Using a Tab Layout all credit goes to Android for their tutorial on how to set up a Tab Layout.
 * https://www.androidhive.info/2015/09/android-material-design-working-with-tabs/
 */

package edu.fsu.mobile.cs.WeatherApp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.List;

import edu.fsu.mobile.cs.WeatherApp.WeatherData.CurrentForecast;
import edu.fsu.mobile.cs.WeatherApp.WeatherData.FutureForecast;
import edu.fsu.mobile.cs.WeatherApp.WeatherData.OnFetchCompleteListener;
import edu.fsu.mobile.cs.WeatherApp.WeatherData.OpenWeatherMapAPI;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Location bestLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


        // Handle location stuff
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.
                checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    0);
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_COARSE_LOCATION},
                    0);

            Log.v("inMain", "we in here");
        }

        LocationManager m = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = m.getProviders(true);
        bestLocation = null;
        for (String provider : providers) {
            Location l = m.getLastKnownLocation(provider);


            if (l == null) {
                continue;
            }
            if (bestLocation == null
                    || l.getAccuracy() < bestLocation.getAccuracy()) {

                bestLocation = l;
            }
        }


        final CurrentForecast currentForecast = new CurrentForecast(bestLocation.getLatitude(), bestLocation.getLongitude());
        OpenWeatherMapAPI.retrieveCurrentForecast(getApplicationContext(), currentForecast,
                new OnFetchCompleteListener() {

            @Override
            public void onFetchComplete() {
                Log.v("tester", currentForecast.getCountry() + "\t" + currentForecast.getCity());
                Log.v("tester", currentForecast.getDescription() + '\n');
                Log.v("tester", currentForecast.getPressure()+ "" + '\n');
            }
        });

        final FutureForecast futureForecast = new FutureForecast(bestLocation.getLatitude(), bestLocation.getLongitude());
        OpenWeatherMapAPI.retrieveFutureForecast(getApplicationContext(), futureForecast,
                new OnFetchCompleteListener() {
                    @Override
                    public void onFetchComplete() {
                        Log.d("testingDesc", futureForecast.getDayHigh(0) + "");
                        Log.d("testingDesc", futureForecast.getDayLow(0) + "");
                    }
                });
    }

    /* Added Fragments Here */
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CurrentForecastFragment(), "TODAY");
        adapter.addFragment(new FutureForecastFragment(), "5 DAYS");
        viewPager.setAdapter(adapter);
    }

}
