package service;

import model.WeatherData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherProcessor {

    public Map<LocalDate, double[]> getDailyMinAndMaxTemperaturesForJanuary(WeatherData weatherData) {
        Map<LocalDate, double[]> dailyTemperatures = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        List<String> times = weatherData.getHourly().getTime();
        List<Double> temperatures = weatherData.getHourly().getTemperature_2m();

        for (int i = 0; i < times.size(); i++) {
            LocalDate date = LocalDate.parse(times.get(i), formatter);

            if (date.getMonthValue() == 1) {
                double temp = temperatures.get(i);

                if (!dailyTemperatures.containsKey(date)) {
                    dailyTemperatures.put(date, new double[]{temp, temp});
                } else {
                    double[] minMax = dailyTemperatures.get(date);
                    if (temp < minMax[0]) {
                        minMax[0] = temp;
                    }
                    if (temp > minMax[1]) {
                        minMax[1] = temp;
                    }
                    dailyTemperatures.put(date, minMax);
                }
            }
        }
        return dailyTemperatures;
    }
}
