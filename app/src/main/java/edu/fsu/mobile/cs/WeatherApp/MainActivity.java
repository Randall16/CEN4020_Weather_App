/**
 * Using a Tab Layout all credit goes to Android for their tutorial on how to set up a Tab Layout.
 * https://www.androidhive.info/2015/09/android-material-design-working-with-tabs/
 */

package edu.fsu.mobile.cs.WeatherApp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import edu.fsu.mobile.cs.WeatherApp.WeatherData.CurrentForecast;
import edu.fsu.mobile.cs.WeatherApp.WeatherData.OnFetchCompleteListener;
import edu.fsu.mobile.cs.WeatherApp.WeatherData.OpenWeatherMapAPI;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

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

        final CurrentForecast currentForecast = new CurrentForecast(30.4383, -84.2807);
        OpenWeatherMapAPI.retrieveCurrentForecast(getApplicationContext(), currentForecast,
                new OnFetchCompleteListener() {

            @Override
            public void onFetchComplete() {
                Log.v("tester", currentForecast.getCountry() + "\t" + currentForecast.getCity());
                Log.v("tester", currentForecast.getDescription() + '\n');
                Log.v("tester", currentForecast.getPressure()+ "" + '\n');
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
