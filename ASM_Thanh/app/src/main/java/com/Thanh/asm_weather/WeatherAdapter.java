package com.Thanh.asm_weather;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter {
    Activity activity;
    List<Item> itemList;
    Convert convert = new Convert();

    public WeatherAdapter(Activity activity, List<Item> itemList) {
        this.activity = activity;
        this.itemList = itemList;
    }
    public void reloadData(List<Item> list){
        this.itemList = list;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = activity.getLayoutInflater().inflate(R.layout.item_weather,parent,false);
        WeatherHolder holder = new WeatherHolder(itemView);
        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        WeatherHolder hd = (WeatherHolder) holder;
        Item model =itemList.get(position);
        hd.tvItemTime.setText(convert.convertTime(model.getDateTime()));
        hd.tvItemTemperature.setText(model.getTemperature().getValue().toString());
        Glide.with(activity).load(convert.convertIconImage(model.getWeatherIcon())).into(hd.ivItemIcon);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
    public class WeatherHolder extends RecyclerView.ViewHolder{
        TextView tvItemTime, tvItemTemperature;
        ImageView ivItemIcon;

        public WeatherHolder(@NonNull View itemView) {
            super(itemView);
            tvItemTime = itemView.findViewById(R.id.tvItemTime);
            tvItemTemperature = itemView.findViewById(R.id.tvItemTemperature);
            ivItemIcon = itemView.findViewById(R.id.ivItemIcon);
        }
    }
}
