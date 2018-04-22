package com.example.henriots.earthquakes.network;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by henriots on 15/12/2017.
 */

public class NetworkManager {
    private Context context;

    public NetworkManager(Context context) {
        this.context = context;
    }

    public static final String URL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_day.geojson";

    public void getEarthquakes(final Response.Listener listener, final Response.ErrorListener errorListener){

        String  REQUEST_TAG = "com.example.henriots.earthquakes.getEarthquakes";

        JsonObjectRequest jsonArrayReq = new JsonObjectRequest(URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                errorListener.onErrorResponse(error);
            }
        });

        // Adding JsonObject request to request queue
        VolleySingleton.getInstance(context).addToRequestQueue(jsonArrayReq, REQUEST_TAG);
    }
}
