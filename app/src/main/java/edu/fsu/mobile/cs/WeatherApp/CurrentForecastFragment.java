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

import edu.fsu.mobile.cs.WeatherApp.WeatherData.CurrentForecast;
import edu.fsu.mobile.cs.WeatherApp.WeatherData.WeatherViewModel;


public class CurrentForecastFragment extends Fragment {

    private WeatherViewModel mModel;

    private TextView cityTextView, dateTextView, temperatureTextView, highTempTextView,
            lowTempTextView, descriptionTextView;
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
                temperatureTextView.setText(String.valueOf(currentForecast.getTemp()));
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

    }

}
