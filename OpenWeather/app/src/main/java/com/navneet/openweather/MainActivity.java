package com.navneet.openweather;


import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.navneet.openweather.Models.WeatherData;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private String appId;
    private String cityName;

    private ImageView weatherIcon;
    private WeatherData weatherData;
    private TextView cityText,descriptionText,tempText,feelsText,
            tempMaxText,tempMinText,windText,pressureText,humidityText,
            cloudinessText;
    private RelativeLayout layer1,progressBar;
    private LinearLayout layer2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherIcon = findViewById(R.id.weather_icon);
        progressBar = findViewById(R.id.progress_bar);

        layer1 = findViewById(R.id.layer_1);
        layer2 = findViewById(R.id.layer_2);

        cityText = findViewById(R.id.city_text);
        descriptionText = findViewById(R.id.description_text);
        tempText = findViewById(R.id.temp_text);
        feelsText = findViewById(R.id.feels_like_text);
        tempMaxText = findViewById(R.id.temp_max_text);
        tempMinText = findViewById(R.id.temp_min_text);
        windText = findViewById(R.id.wind_text);
        pressureText = findViewById(R.id.pressure_text);
        humidityText = findViewById(R.id.humidity_text);
        cloudinessText = findViewById(R.id.cloudiness_text);

        progressBar.setVisibility(View.VISIBLE);

        // OpenWeather api key and city name to fetch data.
        appId = "d09378d73b67ac2bb29520820c29b321";
        cityName = "Mississauga";
        apiRequest();

    }

    void apiRequest() {
        // Current Weather API
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + appId + "&units=metric";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            WeatherData weatherData = new WeatherData();

                            JSONObject main = response.getJSONObject("main");

                            weatherData.setTemp((float) main.getDouble("temp"));
                            weatherData.setTempMax((float) main.getDouble("temp_max"));
                            weatherData.setTempMin((float) main.getDouble("temp_min"));
                            weatherData.setPressure((float) main.getDouble("pressure"));
                            weatherData.setFeelsLike((float) main.getDouble("feels_like"));
                            weatherData.setHumidity((int) main.getDouble("humidity"));

                            JSONObject wind = response.getJSONObject("wind");
                            weatherData.setWindSpeed((float) wind.getDouble("speed"));

                            JSONObject cloud = response.getJSONObject("clouds");
                            weatherData.setCloudiness((int) cloud.getDouble("all"));
                            Long dt = response.getLong("dt");

                            //Convert timestamp to string
                            SimpleDateFormat format = new SimpleDateFormat("MMM dd hh:mma");
                            format.setTimeZone(TimeZone.getDefault());
                            weatherData.setDate(format.format(new Date(dt * 1000)));

                            JSONArray weatherArray = response.getJSONArray("weather");
                            JSONObject weatherJSON = weatherArray.getJSONObject(0);
                            weatherData.setDescription(weatherJSON.getString("description"));
                            weatherData.setIconId(weatherJSON.getString("icon"));


                            String imgUrl = "http://openweathermap.org/img/wn/" +
                                    weatherData.getIconId() + "@4x.png";

                            System.out.println(imgUrl);
                            Picasso.Builder builder = new Picasso.Builder(MainActivity.this);
                            builder.listener(new Picasso.Listener() {
                                @Override
                                public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                                    exception.printStackTrace();
                                }
                            });
                            builder.build().load(imgUrl).resize(150, 150).into(weatherIcon);

                            cityText.setText(cityName);
                            descriptionText.setText(weatherData.getDescription());
                            tempText.setText(weatherData.getTemp() +"°C");
                            feelsText.setText("Feels like "+ weatherData.getFeelsLike() +" °C");
                            tempMaxText.setText(weatherData.getTempMax() +"°C");
                            tempMinText.setText(weatherData.getTempMin() +"°C");
                            windText.setText(weatherData.getWindSpeed() +"m/s");
                            pressureText.setText(weatherData.getPressure() +"hPa");
                            humidityText.setText(weatherData.getHumidity() +"%");
                            cloudinessText.setText(weatherData.getCloudiness() +"%");

                            progressBar.setVisibility(View.GONE);
                            layer1.setVisibility(View.VISIBLE);
                            layer2.setVisibility(View.VISIBLE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Oops! Something went wrong.", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                        layer1.setVisibility(View.VISIBLE);
                        layer2.setVisibility(View.VISIBLE);
                    }
                });

        // Calling the Volley with the help of a request queue
        Volley.newRequestQueue(this).add(jsonObjectRequest);

    }


}