package experiment;

import model.Capital;
import model.WeatherData;
import service.WeatherAPI;
import service.WeatherProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NineThreadExperiment {

    private final WeatherAPI weatherAPI;
    private final WeatherProcessor weatherProcessor;

    public NineThreadExperiment() {
        this.weatherAPI = new WeatherAPI();
        this.weatherProcessor = new WeatherProcessor();
    }

    public void runExperiment() {
        System.out.println("Running NineThreadExperiment...");
        long startTime = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(9);
        List<String> results = new ArrayList<>();

        for (Capital capital : Capital.values()) {
            executor.submit(() -> {
                try {
                    WeatherData weatherData = weatherAPI.fetchWeatherData(capital);
                    Map<String, double[]> dailyMinMaxAvgTemperatures = weatherProcessor.getDailyMinMaxAvgTemperaturesForJanuary(weatherData);

                    results.add(formatTable(capital, dailyMinMaxAvgTemperatures));

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
        System.out.println("Tempo de execucao: " + (endTime - startTime) + "ms");
        System.out.println("NineThreadExperiment concluído.");
    }

    private String formatTable(Capital capital, Map<String, double[]> dailyMinMaxAvgTemperatures) {
        StringBuilder table = new StringBuilder();
        table.append("Temperaturas para ").append(capital.name()).append("\n");
        table.append("------------------------------------------------------------\n");
        table.append("|    Data    | Min Temp (°C) | Max Temp (°C) | Avg Temp (°C) |\n");
        table.append("------------------------------------------------------------\n");

        for (Map.Entry<String, double[]> entry : dailyMinMaxAvgTemperatures.entrySet()) {
            String date = entry.getKey();
            double[] minMaxAvg = entry.getValue();
            table.append(String.format("| %10s | %13.2f | %13.2f | %13.2f |\n", date, minMaxAvg[0], minMaxAvg[1], minMaxAvg[2]));
        }

        table.append("------------------------------------------------------------\n\n");
        return table.toString();
    }

}
