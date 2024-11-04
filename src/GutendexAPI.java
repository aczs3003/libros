package api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import models.Book;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class GutendexAPI {
    private static final String API_URL = "https://gutendex.com/books";

    public List<Book> searchBooksByTitle(String title) throws Exception {
        String urlString = API_URL + "?search=" + title;
        HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() == 200) {
            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            return new Gson().fromJson(jsonObject.get("results"), List.class);
        } else {
            throw new RuntimeException("Failed to connect to API");
        }
    }
}
