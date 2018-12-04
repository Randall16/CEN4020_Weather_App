/**
 * Using a Tab Layout all credit goes to Android for their tutorial on how to set up a Tab Layout.
 * https://www.androidhive.info/2015/09/android-material-design-working-with-tabs/
 */

package edu.fsu.mobile.cs.WeatherApp;

import android.Manifest;
import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import edu.fsu.mobile.cs.WeatherApp.WeatherData.CurrentForecast;
import edu.fsu.mobile.cs.WeatherApp.WeatherData.FutureForecast;
import edu.fsu.mobile.cs.WeatherApp.WeatherData.OnFetchCompleteListener;
import edu.fsu.mobile.cs.WeatherApp.WeatherData.OpenWeatherMapAPI;
import edu.fsu.mobile.cs.WeatherApp.WeatherData.WeatherViewModel;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Location bestLocation;
    private EditText zipCodeEditText;
    private WeatherViewModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        zipCodeEditText = findViewById(R.id.et_zipcode);


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

        LocationManager mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mLocationManager = (LocationManager)getApplicationContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            Location l = mLocationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }

        //bestLocation = .getLastKnownLocation(Context.LOCATION_SERVICE);

        mModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        mModel.updateWeatherWithLocation(bestLocation);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        zipCodeEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId==EditorInfo.IME_ACTION_DONE){

                    int input = Integer.valueOf(zipCodeEditText.getText().toString());
                    mModel.updateWeatherWithZip(input);

                }
                return false;
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
