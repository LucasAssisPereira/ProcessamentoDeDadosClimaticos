package experiment;

import model.Capital;
import model.WeatherData;
import service.WeatherAPI;
import service.WeatherProcessor;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreeThreadExperiment {

    private final WeatherAPI weatherAPI;
    private final WeatherProcessor weatherProcessor;

    public ThreeThreadExperiment() {
        this.weatherAPI = new WeatherAPI();
        this.weatherProcessor = new WeatherProcessor();
    }

    public void runExperiment() {
        System.out.println("Running ThreeThreadExperiment...");

        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (Capital capital : Capital.values()) {
            executor.submit(() -> {
                try {
                    WeatherData weatherData = weatherAPI.fetchWeatherData(capital);
                    Map<LocalDate, double[]> dailyMinMaxTemperatures = weatherProcessor.getDailyMinAndMaxTemperaturesForJanuary(weatherData);

                    printTable(capital, dailyMinMaxTemperatures);

                } catch (Exception e) {
                    System.err.println("Erro ao processar dados para " + capital.name() + ": " + e.getMessage());
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        System.out.println("ThreeThreadExperiment concluído.");
    }

    private void printTable(Capital capital, Map<LocalDate, double[]> dailyMinMaxTemperatures) {
        System.out.println("Temperaturas para " + capital.name());
        System.out.println("---------------------------------------------------");
        System.out.println("|    Data    | Min Temp (°C) | Max Temp (°C) |");
        System.out.println("---------------------------------------------------");

        for (Map.Entry<LocalDate, double[]> entry : dailyMinMaxTemperatures.entrySet()) {
            String date = String.valueOf(entry.getKey());
            double[] minMax = entry.getValue();
            System.out.printf("| %10s | %13.2f | %13.2f |%n", date, minMax[0], minMax[1]);
        }

        System.out.println("---------------------------------------------------\n");
    }

}
