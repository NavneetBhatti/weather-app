package com.navneet.openweather.Models;

public class WeatherData {

    private String date,description,iconId;
    private float temp,feelsLike,tempMax,tempMin,pressure,windSpeed;
    private int humidity,cloudiness;

    public WeatherData(){};

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconId() {
        return iconId;
    }

    public void setIconId(String iconId) {
        this.iconId = iconId;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(float feelsLike) {
        this.feelsLike = feelsLike;
    }

    public float getTempMax() {
        return tempMax;
    }

    public void setTempMax(float tempMax) {
        this.tempMax = tempMax;
    }

    public float getTempMin() {
        return tempMin;
    }

    public void setTempMin(float tempMin) {
        this.tempMin = tempMin;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(int cloudiness) {
        this.cloudiness = cloudiness;
    }


    @Override
    public String toString() {
        return "WeatherData{" +
                "date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", iconId='" + iconId + '\'' +
                ", temp=" + temp +
                ", feelsLike=" + feelsLike +
                ", tempMax=" + tempMax +
                ", tempMin=" + tempMin +
                ", pressure=" + pressure +
                ", windSpeed=" + windSpeed +
                ", humidity=" + humidity +
                ", cloudiness=" + cloudiness +
                '}';
    }
}
