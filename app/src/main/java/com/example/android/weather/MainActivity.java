package com.example.android.weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity
{

    TextView now;
//    TextView today;
//    TextView tom;
    TextView future;


    String currentTemp = "45";
    String todayTempMax = "56/";
    String todayTempMin = "40";
    String tomTempMax = "70/";
    String tomTempMin = "40";
    String futureTempMax = "45/";
    String futureTempMin = "42";
    String futureDay = "";
    String url = "http://api.openweathermap.org/data/2.5/forecast?q=Patchogue,us&cnt=7&units=imperial&appid=c3368eff18484472b806c8fbdf3df950";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateList();

        now = (TextView) findViewById(R.id.now);
        now.setText("Now: " + currentTemp);


        try {

            BufferedReader input = new BufferedReader(new InputStreamReader(new URL(url).openStream(), "UTF-8"));

        } catch (IOException e) {

        }
//        WeatherCaller weather = new WeatherCaller(url);
//        try {
//            BufferedReader input = weather.callURL();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
       // JsonReader reader = new JsonReader();
//
//        today = (TextView) findViewById(R.id.today);
//        today.setText("Today: " + todayTemp);
//
//        tom = (TextView) findViewById(R.id.tomorrow);
//        tom.setText("Tomorrow: " + tomTemp);
//
//        future = (TextView) findViewById(R.id.future);
//        future.setText(futureDay + futureTemp);
    }

    public void getURL() {

    }

    public void parseURL(BufferedReader input)
    {

    }

    public void getFutureDay()
    {

        Calendar calender = Calendar.getInstance();
        int day = calender.get(Calendar.DAY_OF_WEEK);

        switch(day)
        {
            case Calendar.SUNDAY:
                futureDay = "Tuesday: ";
                break;
            case Calendar.MONDAY:
                futureDay = "Wednesday: ";
                break;
            case Calendar.TUESDAY:
                futureDay = "Thursday: ";
                break;
            case Calendar.WEDNESDAY:
                futureDay = "Friday: ";
                break;
            case Calendar.THURSDAY:
                futureDay = "Saturday: ";
                break;
            case Calendar.FRIDAY:
                futureDay = "Sunday: ";
                break;
            case Calendar.SATURDAY:
                futureDay = "Monday: ";
        }
    }

    public void populateList()
    {
        getFutureDay();

        String [] temps = new String[3];
        temps [0] = "Today: " + todayTempMax + todayTempMin;
        temps [1] = "Tomorrow: " + tomTempMax + tomTempMin;
        temps [2] = futureDay + futureTempMax + futureTempMin;

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_test, temps);

        ListView list = (ListView) findViewById(R.id.test);
        list.setAdapter(adapter);
    }


}
