package com.example.android.weather;

import java.util.Date;

/**
 * Created by John on 3/6/2017.
 */

public class WeatherData {
    private double now, temp_min, temp_max, pressure, sea_level;
    private Date date;
    private String description;
    private int humidity;

    public String getDescription() {
        return description;
    }

    public WeatherData setDescription(String description) {
        this.description = description;
        return this;
    }


    public double getNow() {
        return now;
    }

    public WeatherData setNow(double temp) {
        this.now = temp;
        return this;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public WeatherData setTemp_min(double temp_min) {
        this.temp_min = temp_min;
        return this;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public WeatherData setTemp_max(double temp_max) {
        this.temp_max = temp_max;
        return this;
    }

    public double getPressure() {
        return pressure;
    }

    public WeatherData setPressure(double pressure) {
        this.pressure = pressure;
        return this;
    }

    public double getSea_level() {
        return sea_level;
    }

    public WeatherData setSea_level(double sea_level) {
        this.sea_level = sea_level;
        return this;
    }

    public int getHumidity() {
        return humidity;
    }

    public WeatherData setHumidity(int humidity) {
        this.humidity = humidity;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public WeatherData setDate(Date date) {
        this.date = date;
        return this;
    }
}
