package service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.Capital;
import model.WeatherData;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherAPI {

    private static final String API_URL = "https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&hourly=temperature_2m";

    public WeatherData fetchWeatherData(Capital capital) throws Exception {
        String apiUrl = String.format(API_URL, capital.getLatitude(), capital.getLongitude());
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() == 200) {
            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            JsonObject jsonResponse = JsonParser.parseReader(reader).getAsJsonObject();
            Gson gson = new Gson();
            return gson.fromJson(jsonResponse, WeatherData.class);
        } else {
            throw new Exception("Erro ao acessar a API: " + connection.getResponseMessage());
        }
    }
}
