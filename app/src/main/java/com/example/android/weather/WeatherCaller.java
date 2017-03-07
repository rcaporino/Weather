package com.example.android.weather;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by John on 2/27/2017.
 */

public class WeatherCaller extends AsyncTask<String, Void, ArrayList<WeatherData>>{
private String url = "http://api.openweathermap.org/data/2.5/forecast?q=Patchogue,us&cnt=7&units=imperial&appid=c3368eff18484472b806c8fbdf3df950";
private BufferedReader JSONBuffer;

    public WeatherCaller(String newURL){
        url = newURL;
    }

    public void setURL( String newURL){
        url = newURL;
    }

    public String getURL(){
        return url;
    }

    private void callURL() throws Throwable{
        JSONBuffer = new BufferedReader(new InputStreamReader(new URL(url).openStream(), "UTF-8"));
    }

    @Override
    protected ArrayList<WeatherData> doInBackground(String... params) {
        Log.i("LOG", "Attempt Interntet Call");
        try {
            callURL();
            String JSON = JSONBuffer.toString();
            ArrayList<WeatherData> JSONWeather;
            JSONObject forecast = new JSONObject(JSON);
            JSONObject weather = forecast.getJSONObject("temp")
            //PARSE HERE and populate arraylist
            return JSONWeather;



        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
    @Override
    protected void onPostExecute (String JSON) {
        Log.i("LOG", "Attempt Post Execute");
        //Give back the Weather Object
    }
}