package edu.fsu.mobile.cs.WeatherApp.WeatherData;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

public class WeatherViewModel extends AndroidViewModel {

    private CurrentForecast currentForecast;
    private FutureForecast futureForecast;
    private Application application;

    private MutableLiveData<CurrentForecast> currentForecastMutableLiveData;
    private MutableLiveData<FutureForecast> futureForecastMutableLiveData;

    public WeatherViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        currentForecastMutableLiveData = new MutableLiveData<>();
        futureForecastMutableLiveData = new MutableLiveData<>();
    }

    public void updateWeatherWithLocation(Location location) {
       currentForecast = new CurrentForecast(location.getLatitude(), location.getLongitude());
       futureForecast = new FutureForecast(location.getLatitude(), location.getLongitude());

        OpenWeatherMapAPI.retrieveFutureForecast(application, futureForecast,
                new OnFetchCompleteListener() {

            @Override
            public void onFetchComplete() {
                futureForecast.convertToFahrenheit();
                futureForecastMutableLiveData.setValue(futureForecast);
            }
        });

       OpenWeatherMapAPI.retrieveCurrentForecast(application, currentForecast,
               new OnFetchCompleteListener() {

           @Override
           public void onFetchComplete() {
               currentForecast.convertToFahrenheit();
               currentForecastMutableLiveData.setValue(currentForecast);

           }
       });



    }

    public void updateWeatherWithZip(int zipCode) {
        currentForecast = new CurrentForecast(zipCode);
        futureForecast = new FutureForecast(zipCode);

        OpenWeatherMapAPI.retrieveFutureForecast(application, futureForecast,
                new OnFetchCompleteListener() {

                    @Override
                    public void onFetchComplete() {
                        futureForecast.convertToFahrenheit();
                        futureForecastMutableLiveData.setValue(futureForecast);
                    }
                });

        OpenWeatherMapAPI.retrieveCurrentForecast(application, currentForecast,
                new OnFetchCompleteListener() {

                    @Override
                    public void onFetchComplete() {
                        currentForecast.convertToFahrenheit();
                        currentForecastMutableLiveData.setValue(currentForecast);

                    }
                });
    }

    public MutableLiveData<CurrentForecast> getCurrentForecastMutableLiveData() {
        return currentForecastMutableLiveData;
    }

    public MutableLiveData<FutureForecast> getFutureForecastMutableLiveData() {
        return futureForecastMutableLiveData;
    }

    private void toastError() {
        Toast.makeText(application, "API Error", Toast.LENGTH_SHORT).show();
    }

    private void zipError() {
        Toast.makeText(application, "Invalid Zip Code", Toast.LENGTH_SHORT).show();
    }
}
