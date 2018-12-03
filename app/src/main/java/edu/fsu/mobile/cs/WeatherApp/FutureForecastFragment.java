package edu.fsu.mobile.cs.WeatherApp;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

import edu.fsu.mobile.cs.WeatherApp.WeatherData.FutureForecast;
import edu.fsu.mobile.cs.WeatherApp.WeatherData.WeatherViewModel;


public class FutureForecastFragment extends Fragment {
    View v;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<FutureForecastItems> futureList;

    public FutureForecastFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_future_forecast, container, false);
        mRecyclerView = v.findViewById(R.id.future_RecyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new FutureForecastAdapter(getContext(),futureList);
        mRecyclerView.setAdapter(mAdapter);

        WeatherViewModel mModel = ViewModelProviders.of(getActivity()).get(WeatherViewModel.class);

        mModel.getFutureForecastMutableLiveData().observe(getActivity(), new Observer<FutureForecast>() {
            @Override
            public void onChanged(@Nullable FutureForecast futureForecast) {

                futureList.clear();

                for(int i = 0; i < FutureForecast.DAYS_SUPPORTED; i++) {
                    FutureForecastItems f = new FutureForecastItems(
                            ImageUtil.getImage(futureForecast.getDescription(i)),
                            genDate(i), futureForecast.getDescription(i),
                            StringUtil.toString(futureForecast.getDayHigh(i)) + "°F",
                            StringUtil.toString(futureForecast.getDayLow(i)) + "°F");

                    futureList.add(f);
                }

                mAdapter = new FutureForecastAdapter(getContext(),futureList);
                mRecyclerView.setAdapter(mAdapter);
            }
        });

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        futureList = new ArrayList<>();
        /*futureList.add(new FutureForecastItems(R.drawable.clouds, "Sunday, Sep. 16", "cloudy", "95°F", "75°F"));
        futureList.add(new FutureForecastItems(R.drawable.clouds_with_2lighting, "Sunday, Sep. 17", "Chance of thunderstorms", "95°F", "76°F"));
        futureList.add(new FutureForecastItems(R.drawable.clouds, "Sunday, Sep. 18", "cloudy", "95°F", "78°F"));
        futureList.add(new FutureForecastItems(R.drawable.sun, "Sunday, Sep. 19", "sunny", "94°F", "77°F"));
        futureList.add(new FutureForecastItems(R.drawable.clouds_with_lighting, "Sunday, Sep. 20", "Chance of thunderstorms", "91°F", "70°F"));
        */
    }

    private String genDate(int i) {
        switch (i) {
            case 0:
                return "Wednesday, Dec. 5";
            case 1:
                return "Thursday, Dec. 6";
            case 2:
                return "Friday, Dec. 7";
            case 3:
                return "Saturday, Dec. 8";
            case 4:
                return "Sunday, Dec. 9";
        }

        return "";
    }
}
