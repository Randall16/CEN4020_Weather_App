package edu.fsu.mobile.cs.WeatherApp;


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
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        futureList = new ArrayList<>();
        futureList.add(new FutureForecastItems(R.drawable.clouds, "Sunday, Sep. 16", "cloudy", "95°F", "75°F"));
        futureList.add(new FutureForecastItems(R.drawable.clouds_with_2lighting, "Sunday, Sep. 17", "Chance of thunderstorms", "95°F", "76°F"));
        futureList.add(new FutureForecastItems(R.drawable.clouds, "Sunday, Sep. 18", "cloudy", "95°F", "78°F"));
        futureList.add(new FutureForecastItems(R.drawable.sun, "Sunday, Sep. 19", "sunny", "94°F", "77°F"));
        futureList.add(new FutureForecastItems(R.drawable.clouds_with_lighting, "Sunday, Sep. 20", "Chance of thunderstorms", "91°F", "70°F"));
    }
}
