package com.example.henriots.earthquakes.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by henriots on 15/12/2017.
 */

public class Feature {
    @SerializedName("geometry")
    public FeatureGeometry geometry;

    @SerializedName("properties")
    public FeatureProperties properties;

    @SerializedName("id")
    public String id;

    public FeatureGeometry getGeometry() {
        return geometry;
    }

    public void setGeometry(FeatureGeometry geometry) {
        this.geometry = geometry;
    }

}
