package com.Thanh.asm_weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView tvIconPhrase,tvTemperature;
    RecyclerView rvListTemperature;
    List<Item> list;
    WeatherAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvIconPhrase = findViewById(R.id.tvIconPhrase);
        tvTemperature = findViewById(R.id.tvTemperature);
        //B1 : get listdata
        getListData();

        //B2: adapter
        list = new ArrayList<>();
        adapter = new WeatherAdapter(MainActivity.this,list);

        //B3: layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this,RecyclerView.HORIZONTAL,false);

        //B4:
        rvListTemperature = findViewById(R.id.rvListTemperature);
        rvListTemperature.setLayoutManager(layoutManager);
        rvListTemperature.setAdapter(adapter);
    }
    public void getListData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIWeather.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIWeather service = retrofit.create(APIWeather.class);
        service.getListData().enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if(response.body() != null){
                    list = response.body();
                    Item fistdata = response.body().get(0);
                    tvTemperature.setText(fistdata.getTemperature().getValue().toString() + " °");
                    tvIconPhrase.setText(fistdata.getIconPhrase());
                    adapter.reloadData(list);
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {

            }
        });
    }
}