package com.Thanh.asm_weather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIWeather {
    String APIKEY ="wQ77JSlDmCs2WrjmRqmhA8bUetaeEl6Z";
    String SERVER_URL ="http://dataservice.accuweather.com/forecasts/v1/hourly/12hour/";
    String URI = "353412?apikey="+APIKEY+"&language=vi-vn&metric=true";

    @GET(URI)
    Call<List<Item>> getListData();
}
