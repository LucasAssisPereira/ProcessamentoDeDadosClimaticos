package model;
import java.util.List;

public class WeatherData {
    private Hourly hourly;

    public Hourly getHourly() {
        return hourly;
    }

    public static class Hourly {
        private List<String> time;
        private List<Double> temperature_2m;

        public List<String> getTime() {
            return time;
        }

        public List<Double> getTemperature_2m() {
            return temperature_2m;
        }

    }
}