package com.example.weatherapp;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class WeatherResponse {
    @SerializedName("main")
    private Main main;
    @SerializedName("weather")
    private List<Weather> weatherList;

    // Getters and setters for main and weatherList

    public Main getMain() {
        return main;
    }
    public void setMain(Main main) {
        this.main = main;
    }

    public List<Weather> getWeatherList() {
        return weatherList;
    }
    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }



    // Inner class for main
    public class Main {
        @SerializedName("temp")
        private float temperature;
        @SerializedName("humidity")
        private int humidity;

        // Getters and setters for temperature and humidity

        public float getTemperature() {
            return temperature;
        }


        public int getHumidity() {
            return humidity;
        }

        public void setTemperature(float temperature) {
            this.temperature = temperature;
        }
        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }
    }


        // Inner class for weather
    public class Weather {
        @SerializedName("description")
        private String  description;


            public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
    }


}

