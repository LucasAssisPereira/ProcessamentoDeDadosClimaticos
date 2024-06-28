package experiment;

import model.Capital;
import model.WeatherData;
import service.WeatherAPI;
import service.WeatherProcessor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TwentySevenThreadExperiment {

    private final WeatherAPI weatherAPI;
    private final WeatherProcessor weatherProcessor;

    public TwentySevenThreadExperiment() {
        this.weatherAPI = new WeatherAPI();
        this.weatherProcessor = new WeatherProcessor();
    }

    public void runExperiment() {
        System.out.println("Running TwentySevenThreadExperiment...");
        long startTime = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(27);
        List<String> results = new ArrayList<>();

        for (Capital capital : Capital.values()) {
            executor.submit(() -> {
                try {
                    WeatherData weatherData = weatherAPI.fetchWeatherData(capital);
                    Map<LocalDate, double[]> dailyMinMaxTemperatures = weatherProcessor.getDailyMinAndMaxTemperaturesForJanuary(weatherData);

                    results.add(formatTable(capital, dailyMinMaxTemperatures));

                } catch (Exception e) {
                    System.err.println("Erro ao processar dados para " + capital.name() + ": " + e.getMessage());
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            // Aguarda a conclusão de todas as threads
        }

        results.forEach(System.out::println);
        long endTime = System.currentTimeMillis();
        System.out.println("Tempo de execucao:" + (endTime - startTime) + "ms" );
        System.out.println("TwentySevenThreadExperiment concluído.");

    }

    private String formatTable(Capital capital, Map<LocalDate, double[]> dailyMinMaxTemperatures) {
        StringBuilder table = new StringBuilder();
        table.append("Temperaturas para ").append(capital.name()).append("\n");
        table.append("---------------------------------------------------\n");
        table.append("|    Data    | Min Temp (°C) | Max Temp (°C) |\n");
        table.append("---------------------------------------------------\n");

        for (Map.Entry<LocalDate, double[]> entry : dailyMinMaxTemperatures.entrySet()) {
            String date = String.valueOf(entry.getKey());
            double[] minMax = entry.getValue();
            table.append(String.format("| %10s | %13.2f | %13.2f |\n", date, minMax[0], minMax[1]));
        }

        table.append("---------------------------------------------------\n\n");
        return table.toString();
    }

}
