package com.example.henriots.earthquakes.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.henriots.earthquakes.R;
import com.example.henriots.earthquakes.model.Feature;

import java.util.List;

/**
 * Created by henriots on 15/12/2017.
 */

/**
 * Using RecyclerView because it uses Viewholder pattern by default. If list items become larger
 * it becomes useful. It would also be wise to implement paging if had more time.
 */
public class EarthquakeAdapter extends RecyclerView.Adapter<EarthquakeAdapter.EarthquakeViewHolder> {

    private List<Feature> earthquakes;
    private OnItemClickListener onItemClickListener;

    public EarthquakeAdapter(List<Feature> earthquakes) {
        this.earthquakes = earthquakes;
    }


    @Override
    public EarthquakeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_earthquake, parent, false);

        return new EarthquakeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EarthquakeViewHolder holder, int position) {
        final Feature earthquake = earthquakes.get(position);
        holder.place.setText(earthquake.properties.getPlace());

        //Setting onClick listener because recyclerview does not have that built in
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(earthquake);
            }
        };
        holder.itemView.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return earthquakes.size();
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public List<Feature> getEarthquakes() {
        return earthquakes;
    }

    public void setEarthquakes(List<Feature> earthquakes) {
        this.earthquakes = earthquakes;
    }

    public interface OnItemClickListener {
        void onItemClick(Feature item);
    }

    public class EarthquakeViewHolder extends RecyclerView.ViewHolder {
        public TextView place;

        public EarthquakeViewHolder(View view) {
            super(view);
            place = (TextView) view.findViewById(R.id.place);
        }
    }
}
