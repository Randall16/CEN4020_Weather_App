package edu.fsu.mobile.cs.WeatherApp;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import edu.fsu.mobile.cs.WeatherApp.WeatherData.CurrentForecast;
import edu.fsu.mobile.cs.WeatherApp.WeatherData.WeatherViewModel;


public class CurrentForecastFragment extends Fragment {

    private WeatherViewModel mModel;

    private TextView cityTextView, dateTextView, temperatureTextView, highTempTextView,
            lowTempTextView, descriptionTextView, humidityTextView;
    private ImageView iconImageView;

    public CurrentForecastFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_current_forecast, container, false);
        attachIDs(view);

        mModel = ViewModelProviders.of(getActivity()).get(WeatherViewModel.class);

        mModel.getCurrentForecastMutableLiveData().observe(getActivity(), new Observer<CurrentForecast>() {
            @Override
            public void onChanged(@Nullable CurrentForecast currentForecast) {
                temperatureTextView.setText(StringUtil.toString(currentForecast.getTemp()) + "\u00b0"
                        + currentForecast.getMetric());
                highTempTextView.setText(StringUtil.toString(currentForecast.getTemp_max()));
                lowTempTextView.setText(StringUtil.toString(currentForecast.getTemp_min()));
                String str = currentForecast.getDescription();
                descriptionTextView.setText(str.substring(0, 1).toUpperCase() + str.substring(1));
                cityTextView.setText(currentForecast.getCity());
                SimpleDateFormat f = new SimpleDateFormat("E, h:mm a z");
                dateTextView.setText(f.format(Calendar.getInstance().getTime()));

                iconImageView.setImageResource(ImageUtil.getImage(currentForecast.getDescription()));
                humidityTextView.setText("Humidity level " +
                        StringUtil.toString(currentForecast.getHumidity()) + "%");
            }
        });


        return view;
    }

    private void attachIDs(View view) {
        cityTextView = view.findViewById(R.id.tv_city);
        dateTextView = view.findViewById(R.id.tv_date);
        temperatureTextView = view.findViewById(R.id.tv_temp);
        highTempTextView = view.findViewById(R.id.tv_highTemp);
        lowTempTextView = view.findViewById(R.id.tv_lowTemp);
        descriptionTextView = view.findViewById(R.id.tv_currentDescription);
        iconImageView = view.findViewById(R.id.iv_icon);
        humidityTextView = view.findViewById(R.id.tv_h);

    }

}
