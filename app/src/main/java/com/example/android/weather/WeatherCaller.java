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

public class WeatherCaller extends AsyncTask<String, String, ArrayList<WeatherData>>{
private String url = "http://api.openweathermap.org/data/2.5/forecast/daily?q=Patchogue,us&cnt=7&units=imperial&appid=c3368eff18484472b806c8fbdf3df950";
private BufferedReader JSONBuffer;

    public WeatherCaller(String newURL){
        url = newURL;
    }
    public WeatherCaller (){}
    public WeatherCaller(Handoff handoff){
        this.handoff = handoff;
    }

    public interface Handoff{
        void backToMainActivity(ArrayList<WeatherData> w);
    }
    public Handoff handoff = null;

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
        Log.i("BakerCALL", "Attempt Internet Call");
        try {
            Log.i("BAKERCALL", "CALLING");
            String json = callURL();
            Log.i("BakerJSONRAW", json);

            ArrayList<WeatherData> jsonWeather = new ArrayList<WeatherData>();
            JSONObject forecast = new JSONObject(json);
            JSONArray weather = forecast.getJSONArray("list");

            for(int i =0; i<weather.length(); i++){
                JSONObject w = weather.getJSONObject(i);
                JSONObject temp = w.getJSONObject("temp");
                JSONArray subWeather = w.getJSONArray("weather");
                WeatherData data = new WeatherData()
//                        .setTemp(main.getDouble("temp"))
                        .setTemp_min(temp.getDouble("min"))
                        .setTemp_max(temp.getDouble("max"))
                        .setPressure(w.getDouble("pressure"))
//                        .setSea_level(main.getDouble("sea_level"))
                        .setHumidity(w.getInt("humidity"))
                        .setDate(new Date(w.getLong("dt")*1000L))
                        .setDescription(subWeather.getJSONObject(0).getString("description"));
                jsonWeather.add(data);
                Log.i("BakerDESCRIPTIONS", data.getDescription());
            }

            Log.i("BakerFORECASTDAYS", jsonWeather.size() + "");
            Log.i("BakerJsonRelevantDATA", weather.toString());

            return jsonWeather;



        } catch (Exception e) {
            Log.e ("BakerEXCEPTION","Exception Thrown, Debug", e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(ArrayList<WeatherData> weatherData) {

        super.onPostExecute(weatherData);
        Log.i("BakerPOST", "Attempt Post Execute");
        handoff.backToMainActivity(weatherData);
    }
}