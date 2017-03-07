package com.example.android.weather;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by John on 2/27/2017.
 */

public class WeatherCaller extends AsyncTask<String, Void, ArrayList<WeatherData>>{
private String url = "http://api.openweathermap.org/data/2.5/forecast/daily?q=Patchogue,us&cnt=7&units=imperial&appid=c3368eff18484472b806c8fbdf3df950";
private BufferedReader JSONBuffer;

    public WeatherCaller(String newURL){
        url = newURL;
    }
    public WeatherCaller (){

    }

    public void setURL( String newURL){
        url = newURL;
    }

    public String getURL(){
        return url;
    }

    private String callURL() throws Exception{
        BufferedReader getJsonData = new BufferedReader(new InputStreamReader(new URL(url).openStream(), "UTF-8"));
        String line = "";

        StringBuilder sb = new StringBuilder();
        while ((line = getJsonData.readLine()) !=null){
            sb.append(line);
        }
        return sb.toString();
    }

    @Override
    protected ArrayList<WeatherData> doInBackground(String... params) {
        Log.i("BAKER", "Attempt Internet Call");
        try {
            String json = callURL();
            Log.i("BAKER!", json);

            ArrayList<WeatherData> jsonWeather = new ArrayList<WeatherData>();
            JSONObject forecast = new JSONObject(json);
            JSONArray weather = forecast.getJSONArray("list");

            for(int i =0; i<weather.length(); i++){
                JSONObject w = weather.getJSONObject(i);
                JSONObject temp = w.getJSONObject("temp");
                WeatherData data = new WeatherData()
//                        .setTemp(main.getDouble("temp"))
                        .setTemp_min(temp.getDouble("min"))
                        .setTemp_max(temp.getDouble("max"))
                        .setPressure(w.getDouble("pressure"))
//                        .setSea_level(main.getDouble("sea_level"))
                        .setHumidity(w.getInt("humidity"))
                        .setDate(new Date(w.getLong("dt")*1000L));
                jsonWeather.add(data);
                Log.i("BakerWeathers", data.getDate().toString());
            }

            Log.i("Baker", jsonWeather.size() + "");
            Log.i("Baker", weather.toString());


            return jsonWeather;



        } catch (Exception e) {
            Log.e ("Baker","Exception", e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(ArrayList<WeatherData> weatherDatas) {

        super.onPostExecute(weatherDatas);
        Log.i("LOG", "Attempt Post Execute");
    }
}