package com.example.henriots.earthquakes.model;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by henriots on 15/12/2017.
 */

public class FeatureProperties {
    @SerializedName("place")
    public String place;

    @SerializedName("time")
    public long time;

    public String formattedTime() {
        Date date = new Date(time);
        DateFormat formatter = new SimpleDateFormat("dd.MM HH:mm");
        return formatter.format(date);
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }



}
