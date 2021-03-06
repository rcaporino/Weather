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
private String urlToday = "http://api.openweathermap.org/data/2.5/weather?q=Patchogue&units=imperial&appid=c3368eff18484472b806c8fbdf3df950";
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

    private String callURL(String url) throws Exception{
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
            String json = callURL(url);
            Log.i("BakerJSONRAW", json);

            String jsonNow = callURL(urlToday);
            JSONObject objectNow = new JSONObject (jsonNow);
            JSONObject mainNow = objectNow.getJSONObject("main");


            ArrayList<WeatherData> jsonWeather = new ArrayList<WeatherData>();
            JSONObject forecast = new JSONObject(json);
            JSONArray weather = forecast.getJSONArray("list");

            for(int i =0; i<weather.length(); i++){
                JSONObject w = weather.getJSONObject(i);
                JSONObject temp = w.getJSONObject("temp");
                JSONArray subWeather = w.getJSONArray("weather");
                if(i==0) {
                    WeatherData data = new WeatherData()
                            .setTemp_min(mainNow.getDouble("temp_min"))
                            .setTemp_max(mainNow.getDouble("temp_max"))
                            .setPressure(w.getDouble("pressure"))
                            .setHumidity(mainNow.getInt("humidity"))
                            .setDate(new Date(w.getLong("dt") * 1000L))
                            .setDescription(subWeather.getJSONObject(0).getString("description"))
                            .setNow(mainNow.getDouble("temp"));
                    jsonWeather.add(data);

                } else{
                    WeatherData data = new WeatherData()
                            .setTemp_min(temp.getDouble("min"))
                            .setTemp_max(temp.getDouble("max"))
                            .setPressure(w.getDouble("pressure"))
                            .setHumidity(w.getInt("humidity"))
                            .setDate(new Date(w.getLong("dt") * 1000L))
                            .setDescription(subWeather.getJSONObject(0).getString("description"));
                    jsonWeather.add(data);
                    Log.i("BakerDESCRIPTIONS", data.getDescription());
                }
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