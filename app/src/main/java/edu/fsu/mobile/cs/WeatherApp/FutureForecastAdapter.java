package edu.fsu.mobile.cs.WeatherApp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FutureForecastAdapter extends RecyclerView.Adapter<FutureForecastAdapter.MyViewHolder> {

    Context mcontext;
    List<FutureForecastItems> futureForecastList;

    public FutureForecastAdapter(Context mcontext, List<FutureForecastItems> futureForecastList) {
        this.mcontext = mcontext;
        this.futureForecastList = futureForecastList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mcontext).inflate(R.layout.weather_cardview_layout, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mImageView.setImageResource(futureForecastList.get(position).getWeatherIcon());
        holder.mTextView1.setText(futureForecastList.get(position).getmDate());
        holder.mTextView2.setText(futureForecastList.get(position).getmWeather_descript());
        holder.mTextView3.setText(futureForecastList.get(position).getmTemp_hi());
        holder.mTextView4.setText(futureForecastList.get(position).getmTemp_lo());
    }

    @Override
    public int getItemCount() {
        return futureForecastList.size();
    }
    //This stuff goes in the Weather_cardview_layout.xml: <- just wanted to see what the icon would look like without it
    //android:layout_marginEnd="97dp"
    //android:layout_marginStart="59dp"
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImageView;
        private TextView mTextView1;
        private TextView mTextView2;
        private TextView mTextView3;
        private TextView mTextView4;


        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.weather1_icon);
            mTextView1 = itemView.findViewById(R.id.weather1_date);
            mTextView2 = itemView.findViewById(R.id.weather1_description);
            mTextView3 = itemView.findViewById(R.id.weather1_tempHi);
            mTextView4 = itemView.findViewById(R.id.weather1_tempLo);
        }
    }
}
