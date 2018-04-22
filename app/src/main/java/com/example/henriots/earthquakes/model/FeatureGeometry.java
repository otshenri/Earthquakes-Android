package com.example.henriots.earthquakes.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by henriots on 15/12/2017.
 */

public class FeatureGeometry {
    @SerializedName("coordinates")
    public double[] coordinates;

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }
}
