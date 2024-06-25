package model;

import java.util.List;

public class WeatherData {
    private List<Double> temperature_2m;

    public List<Double> getTemperature2m() {
        return temperature_2m;
    }

    public void setTemperature2m(List<Double> temperature_2m) {
        this.temperature_2m = temperature_2m;
    }
}
