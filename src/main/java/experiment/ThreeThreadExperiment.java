package experiment;

import model.Capital;
import service.WeatherAPI;
import service.WeatherProcessor;
import model.WeatherData;

import java.util.Arrays;
import java.util.List;

import static model.Capital.*;

public class ThreeThreadExperiment {

    public void runExperiment() {
        List<Capital> capitals = Arrays.asList(values());
        WeatherAPI weatherAPI = new WeatherAPI();
        WeatherProcessor weatherProcessor = new WeatherProcessor();

        String[] headers = {"Capital", "Min Temp (°C)", "Max Temp (°C)", "Avg Temp (°C)"};
        String[][] data = new String[capitals.size()][headers.length];

        int partitionSize = capitals.size() / 3;
        Thread[] threads = new Thread[3];

        for (int i = 0; i < 3; i++) {
            final int start = i * partitionSize;
            final int end = (i == 2) ? capitals.size() : (i + 1) * partitionSize;
            threads[i] = new Thread(() -> processCapitals(weatherAPI, weatherProcessor, capitals.subList(start, end), data, start));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Imprimir a tabela
        printTable(headers, data);
    }

    private void processCapitals(WeatherAPI weatherAPI, WeatherProcessor weatherProcessor, List<Capital> capitals, String[][] data, int offset) {
        int index = offset;
        for (Capital capital : capitals) {
            try {
                // Obter dados do clima para a capital
                WeatherData weatherData = weatherAPI.fetchWeatherData(capital);

                // Processar dados do clima
                double minTemp = weatherProcessor.calculateMinTemperature(weatherData);
                double maxTemp = weatherProcessor.calculateMaxTemperature(weatherData);
                double avgTemp = weatherProcessor.calculateAverageTemperature(weatherData);

                // Armazenar os dados na tabela
                data[index][0] = capital.name();
                data[index][1] = String.format("%.2f", minTemp);
                data[index][2] = String.format("%.2f", maxTemp);
                data[index][3] = String.format("%.2f", avgTemp);
                index++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void printTable(String[] headers, String[][] data) {
        // Calcular a largura máxima de cada coluna
        int[] columnWidths = new int[headers.length];
        for (int i = 0; i < headers.length; i++) {
            columnWidths[i] = headers[i].length();
        }
        for (String[] row : data) {
            for (int i = 0; i < row.length; i++) {
                columnWidths[i] = Math.max(columnWidths[i], row[i].length());
            }
        }

        // Imprimir o cabeçalho
        printRow(headers, columnWidths);
        printSeparator(columnWidths);

        // Imprimir as linhas de dados
        for (String[] row : data) {
            printRow(row, columnWidths);
        }
    }

    private void printRow(String[] row, int[] columnWidths) {
        StringBuilder rowBuilder = new StringBuilder();
        for (int i = 0; i < row.length; i++) {
            rowBuilder.append(String.format(" %-" + columnWidths[i] + "s ", row[i]));
            if (i < row.length - 1) {
                rowBuilder.append("|");
            }
        }
        System.out.println(rowBuilder.toString());
    }

    private void printSeparator(int[] columnWidths) {
        StringBuilder separatorBuilder = new StringBuilder();
        for (int width : columnWidths) {
            for (int i = 0; i < width + 2; i++) {
                separatorBuilder.append("-");
            }
            separatorBuilder.append("+");
        }
        separatorBuilder.setLength(separatorBuilder.length() - 1);
        System.out.println(separatorBuilder.toString());
    }
}
