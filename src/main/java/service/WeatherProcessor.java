package service;

import model.WeatherData;

import java.util.List;

public class WeatherProcessor {

    public double calculateMinTemperature(WeatherData weatherData) {
        return weatherData.getHourly().getTemperature_2m().stream().min(Double::compare).orElse(Double.NaN);
    }
    public double calculateMaxTemperature(WeatherData weatherData) {
        return weatherData.getHourly().getTemperature_2m().stream().max(Double::compare).orElse(Double.NaN);
    }

    public double calculateAverageTemperature(WeatherData weatherData) {
        List<Double> temperatures = weatherData.getHourly().getTemperature_2m();
        return temperatures.stream().mapToDouble(Double::doubleValue).average().orElse(Double.NaN);
    }
}
