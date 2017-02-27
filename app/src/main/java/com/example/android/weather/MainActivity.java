package com.example.android.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.util.*;

public class MainActivity extends AppCompatActivity
{

    TextView now;
//    TextView today;
//    TextView tom;
    TextView future;


    String currentTemp = "Now: 45";
    String todayTemp = "Today: 56/40";
    String tomTemp = "Tomorrow: 70/40";
    String futureTemp = "45/42";
    String futureDay = "";



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateList();

        now = (TextView) findViewById(R.id.now);
        now.setText(currentTemp);
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
        temps [0] = todayTemp;
        temps [1] = tomTemp;
        temps [2] = futureDay + futureTemp;

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_test, temps);

        ListView list = (ListView) findViewById(R.id.test);
        list.setAdapter(adapter);
    }

}
