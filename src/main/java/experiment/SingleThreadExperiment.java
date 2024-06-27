package experiment;

import model.Capital;
import model.WeatherData;
import service.WeatherAPI;
import service.WeatherProcessor;

import java.util.Arrays;
import java.util.List;

public class SingleThreadExperiment {
    public void runExperiment() {
        List<Capital> capitals = Arrays.asList(Capital.values());
        WeatherAPI weatherAPI = new WeatherAPI();
        WeatherProcessor weatherProcessor = new WeatherProcessor();

        String[] headers = {"Capital", "Min Temp (°C)", "Max Temp (°C)", "Avg Temp (°C)"};
        String[][] data = new String[capitals.size()][headers.length];

        int index = 0;
        for (Capital capital : capitals) {
            try {

                WeatherData weatherData = weatherAPI.fetchWeatherData(capital);

                double minTemp = weatherProcessor.calculateMinTemperature(weatherData);
                double maxTemp = weatherProcessor.calculateMaxTemperature(weatherData);
                double avgTemp = weatherProcessor.calculateAverageTemperature(weatherData);


                data[index][0] = capital.name();
                data[index][1] = String.format("%.2f", minTemp);
                data[index][2] = String.format("%.2f", maxTemp);
                data[index][3] = String.format("%.2f", avgTemp);
                index++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Imprimir a tabela
        printTable(headers, data);
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
