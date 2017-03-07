package com.example.android.weather;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by John on 2/27/2017.
 */

public class WeatherCaller extends AsyncTask<String, Void, BufferedReader>{
private String url = "http://api.openweathermap.org/data/2.5/forecast?q=Patchogue,us&cnt=7&units=imperial&appid=c3368eff18484472b806c8fbdf3df950";

    public WeatherCaller(String newURL){
        url = newURL;
    }

    public void setURL( String newURL){
        url = newURL;
    }

    public String getURL(){
        return url;
    }

    public BufferedReader callURL() throws Throwable{
        BufferedReader input = new BufferedReader(new InputStreamReader(new URL(url).openStream(), "UTF-8"));
        return input;
    }

    @Override
    protected BufferedReader doInBackground(String... params) {
        Log.i("LOG", "Attempt Interntet Call");
        try {
            return callURL();

        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
}
