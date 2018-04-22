package com.example.henriots.earthquakes.controller;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.henriots.earthquakes.R;
import com.example.henriots.earthquakes.adapter.EarthquakeAdapter;
import com.example.henriots.earthquakes.model.Feature;
import com.example.henriots.earthquakes.network.NetworkManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by henriots on 15/12/2017.
 */

public class EarthquakeListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private static final String TAG = "EarthquakeListFragment";

    private NetworkManager mNetworkManager;
    private RecyclerView recyclerView;
    private EarthquakeAdapter earthquakeAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.earthquakelist_fragment, container, false);

        mNetworkManager = new NetworkManager(getActivity().getApplicationContext());
        mSwipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        setupEarthquakeList(root);
        requestEarthquakes();
        return root;
    }

    private void requestEarthquakes() {
        mNetworkManager.getEarthquakes(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                List<Feature> earthquakes = parseJSON(response);
                earthquakeAdapter.setEarthquakes(earthquakes);
                earthquakeAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error: " + error.getMessage());
            }
        });
    }

    private List<Feature> parseJSON(JSONObject json) {
        List<Feature> earthquakes = new ArrayList<>();
        Gson gson = new Gson();
        try {
            JSONArray features = json.getJSONArray("features");
            Type listType = new TypeToken<List<Feature>>(){}.getType();
            earthquakes = (List<Feature>) gson.fromJson(features.toString(), listType);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return earthquakes;
    }

    private void setupEarthquakeList(View root) {
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view_earthquakes);
        earthquakeAdapter = new EarthquakeAdapter(new ArrayList<Feature>());
        addOnClickListener(earthquakeAdapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity()
                .getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(earthquakeAdapter);
    }

    private void addOnClickListener(EarthquakeAdapter adapter) {
        adapter.setOnItemClickListener(new EarthquakeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Feature item) {
                MapFragment mapFragment= new MapFragment();
                mapFragment.setEarthquake(item);
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, mapFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public void onRefresh() {
        requestEarthquakes();
    }

    //TODO: Implement loading indicator for first time loading as well
}
