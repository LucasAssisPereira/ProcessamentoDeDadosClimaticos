package service;

import model.WeatherData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherProcessor {

    public Map<String, double[]> getDailyMinMaxAvgTemperaturesForJanuary(WeatherData weatherData) {
        Map<String, double[]> dailyMinMaxAvgTemperatures = new HashMap<>();

        List<String> times = weatherData.getHourly().getTime();
        List<Double> temperatures = weatherData.getHourly().getTemperature_2m();

        for (int i = 0; i < times.size(); i++) {
            String date = times.get(i).split("T")[0];

            double temperature = temperatures.get(i);

            if (!dailyMinMaxAvgTemperatures.containsKey(date)) {
                dailyMinMaxAvgTemperatures.put(date, new double[]{temperature, temperature, temperature, 1});
            } else {
                double[] minMaxAvg = dailyMinMaxAvgTemperatures.get(date);
                minMaxAvg[0] = Math.min(minMaxAvg[0], temperature);
                minMaxAvg[1] = Math.max(minMaxAvg[1], temperature);
                minMaxAvg[2] += temperature;
                minMaxAvg[3] += 1;
            }
        }

        for (Map.Entry<String, double[]> entry : dailyMinMaxAvgTemperatures.entrySet()) {
            double[] minMaxAvg = entry.getValue();
            minMaxAvg[2] = minMaxAvg[2] / minMaxAvg[3];
        }

        return dailyMinMaxAvgTemperatures;
    }
}
