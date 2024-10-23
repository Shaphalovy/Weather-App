package com.example.weatherapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private EditText etCityName;
    private Button btnGetWeather;
    private TextView tvTemperature, tvHumidity, tvDescription;
    private WeatherApi weatherApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etCityName = findViewById(R.id.et_city_name);
        btnGetWeather = findViewById(R.id.btn_get_weather);
        tvTemperature = findViewById(R.id.tv_temperature);
        tvHumidity = findViewById(R.id.tv_humidity);
        tvDescription = findViewById(R.id.tv_description);

        weatherApi= RetrofitClient.getClient().create(WeatherApi.class);
        
        btnGetWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchWeather();
            }
        });
    }

    private void fetchWeather() {
        String cityName = etCityName.getText().toString();
    if (cityName.isEmpty()){
        Toast.makeText(this, "Please enter city name", Toast.LENGTH_SHORT).show();
        }
    Call<WeatherResponse> call = weatherApi.getWeather(cityName, "7c2d6bce1c7b1b65cff35b47d05fe75f");
    call.enqueue(new Callback<WeatherResponse>() {
        @Override
        public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
            if (response.isSuccessful() && response.body() != null){
                WeatherResponse weatherResponse = response.body();

                tvTemperature.setText("Temperatue: "+weatherResponse.getMain().getTemperature()+"°C");
                tvHumidity.setText("Humidity: "+weatherResponse.getMain().getHumidity()+"%");

                if (weatherResponse.getWeatherList() != null && !weatherResponse.getWeatherList().isEmpty()) {
                   
                    tvDescription.setText("Description: " + weatherResponse.getWeatherList().get(0).getDescription());
                } else {
                    tvDescription.setText("Description not available.");
                }

            }
            else {
                Toast.makeText(MainActivity.this, "Error fetching weather", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<WeatherResponse> call, Throwable t) {
            Toast.makeText(MainActivity.this, "Error fetching weather", Toast.LENGTH_SHORT).show();
        }
    });


    }


}