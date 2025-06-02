package com.example.weathertrack.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.weathertrack.R;
import com.example.weathertrack.data.WeatherEntity;
import java.text.DecimalFormat;

public class WeatherAdapter extends ListAdapter<WeatherEntity, WeatherAdapter.WeatherViewHolder> {
    
    private static final DiffUtil.ItemCallback<WeatherEntity> DIFF_CALLBACK = 
        new DiffUtil.ItemCallback<WeatherEntity>() {
            @Override
            public boolean areItemsTheSame(@NonNull WeatherEntity oldItem, @NonNull WeatherEntity newItem) {
                return oldItem.id == newItem.id;
            }

            @Override
            public boolean areContentsTheSame(@NonNull WeatherEntity oldItem, @NonNull WeatherEntity newItem) {
                return oldItem.date.equals(newItem.date) && 
                       oldItem.temperature == newItem.temperature &&
                       oldItem.condition.equals(newItem.condition);
            }
        };

    public WeatherAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_weather, parent, false);
        return new WeatherViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        WeatherEntity weather = getItem(position);
        holder.bind(weather);
    }

    static class WeatherViewHolder extends RecyclerView.ViewHolder {
        private final TextView dateText;
        private final TextView temperatureText;
        private final TextView conditionText;
        private final TextView humidityText;
        private final TextView descriptionText;
        private final DecimalFormat tempFormat = new DecimalFormat("#.#");

        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            dateText = itemView.findViewById(R.id.tv_date);
            temperatureText = itemView.findViewById(R.id.tv_temperature);
            conditionText = itemView.findViewById(R.id.tv_condition);
            humidityText = itemView.findViewById(R.id.tv_humidity);
            descriptionText = itemView.findViewById(R.id.tv_description);
        }

        public void bind(WeatherEntity weather) {
            dateText.setText(weather.date);
            temperatureText.setText(tempFormat.format(weather.temperature) + "°C");
            conditionText.setText(weather.condition);
            humidityText.setText("Humidity: " + weather.humidity + "%");
            descriptionText.setText(weather.description);
        }
    }
}