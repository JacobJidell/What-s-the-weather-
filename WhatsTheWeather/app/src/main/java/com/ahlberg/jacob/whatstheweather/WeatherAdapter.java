package com.ahlberg.jacob.whatstheweather;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ahlberg.jacob.whatstheweather.model.DailyWeatherReport;
import com.ahlberg.jacob.whatstheweather.model.WeeklyWeather;
import com.ahlberg.jacob.whatstheweather.model.WeeklyWeatherReport;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Jacob on 2017-03-05.
 * What's The Weather?
 * SMHI API
 */

class WeatherAdapter extends RecyclerView.Adapter<WeatherReportViewHolder> {
    private Context context;
    private ArrayList<WeeklyWeatherReport> weeklyWeatherReports;
    private int whichDay = 1;


    WeatherAdapter(ArrayList<WeeklyWeatherReport> weeklyWeatherReport, Context context) {
        this.weeklyWeatherReports = weeklyWeatherReport;
        this.context = context;
    }

    @Override
    public WeatherReportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_weather, parent, false);
        return new WeatherReportViewHolder(card, parent.getContext());
    }

    @Override
    public void onBindViewHolder(WeatherReportViewHolder holder, int position) {
        WeeklyWeatherReport report = weeklyWeatherReports.get(position);
        holder.weatherIcon.setImageDrawable(report.getDrawableToList(context));

        Boolean degree = PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean("degrees", false);
        String tempMin = "" + WeeklyWeatherReport.getRightDegreeMin(report.getTemperatureMin(), degree);
        holder.tempLow.setText(tempMin);
        String tempMax = "" + WeeklyWeatherReport.getRightDegreeMax(report.getTemperatureMax(), degree);
        holder.tempHigh.setText(tempMax);

        holder.weatherDescription.setText(report.getSummary());
        switch (position){
            case 0: whichDay = 1; break;
            case 1: whichDay = 2; break;
            case 2: whichDay = 3; break;
            case 3: whichDay = 4; break;
            case 4: whichDay = 5; break;
            case 5: whichDay = 6; break;
            case 6: whichDay = 7; break;
            default: whichDay = 8; break;
        }
        String day = WeeklyWeatherReport.getDaysOfWeek(whichDay);
        holder.weatherDate.setText(day);

    }

    @Override
    public int getItemCount() {
        return weeklyWeatherReports.size();
    }
}
