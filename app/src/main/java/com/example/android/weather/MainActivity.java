package com.example.android.weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements WeatherCaller.Handoff {
    ArrayList<WeatherData> forecast;
    TextView now;
    TextView todayHighLow;
    TextView humidity;
    //    TextView today;
//    TextView tom;
    TextView future;

    boolean threadFinish = false;
    String futureDay = "";
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String heading = "More Details";
        TextView weatherAppText = (TextView) findViewById(R.id.more_Details);
        SpannableString spanString = new SpannableString(heading);
        spanString.setSpan(new UnderlineSpan(), 0, spanString.length(), 0);
        weatherAppText.setText(spanString);
        //TODO TEMP CODE


        new WeatherCaller(this).execute();


    }

    @Override
    public void backToMainActivity(ArrayList<WeatherData> w) {
        forecast = w;
        Log.i("Baker", "merged data from caller");
        setTodaysValues();
        populateList();
        threadFinish = true;
    }


    public void setTodaysValues() {
        WeatherData today = forecast.get(0);
        now = (TextView) findViewById(R.id.now);
        now.setText("Now: " + today.getNow());

        humidity = (TextView) findViewById(R.id.humidity);
        humidity.setText("Humidity: " + today.getHumidity());

        todayHighLow = (TextView) findViewById(R.id.todayHighLow);
        Double max = today.getTemp_max();
        int maxInt = max.intValue();
        Double min = today.getTemp_min();
        int minInt = min.intValue();
        todayHighLow.setText("High/Low: " + maxInt + "/" + minInt);

    }

    public void getFutureDay() {

        Calendar calender = Calendar.getInstance();
        int day = calender.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.SUNDAY:
                futureDay = "Tuesday: ";
                counter = 1;
                break;
            case Calendar.MONDAY:
                futureDay = "Wednesday: ";
                counter = 2;
                break;
            case Calendar.TUESDAY:
                futureDay = "Thursday: ";
                counter = 3;
                break;
            case Calendar.WEDNESDAY:
                futureDay = "Friday: ";
                counter = 4;
                break;
            case Calendar.THURSDAY:
                futureDay = "Saturday: ";
                counter = 5;
                break;
            case Calendar.FRIDAY:
                futureDay = "Sunday: ";
                counter = 6;
                break;
            case Calendar.SATURDAY:
                counter = 7;
                futureDay = "Monday: ";
        }
    }


    public void populateList() {
        getFutureDay();

        ArrayList<String> tempsList = new ArrayList<String>();

        tempsList.add("Tomorrow: " + truncate(forecast.get(1).getTemp_max()) + "/" + truncate(forecast.get(1).getTemp_min()));
        for (int i = 0; i < forecast.size() - 2; i++) {
            tempsList.add(dayNames(i) + truncate(forecast.get(i + 2).getTemp_max()) + "/" + truncate(forecast.get(i + 2).getTemp_min()));
        }

        // tempsList.add()
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_test, tempsList);

        ListView list = (ListView) findViewById(R.id.test);
        list.setAdapter(adapter);
    }

    public int truncate(double trun) {
        Double x = trun;
        int y = x.intValue();
        return y;
    }

    public String dayNames(int x) {
        int y = x + counter;
        String ret = "";
        switch (y % 7) {
            case 1:
                ret = "Tuesday: ";
                break;

            case 2:
                ret = "Wednesday: ";
                break;

            case 3:
                ret = "Thursday: ";
                break;

            case 4:
                ret = "Friday: ";
                break;

            case 5:
                ret = "Saturday: ";
                break;

            case 6:
                ret = "Sunday: ";
                break;

            case 7:
                ret = "Monday: ";

        }
        return ret;
    }

}
