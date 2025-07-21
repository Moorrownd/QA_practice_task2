package com.example.api.tests;
import org.junit.jupiter.api.Test;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavaHttpTests {

    @Test
    public void testGetRequest() throws Exception {
        URL url = new URL("https://jsonplaceholder.typicode.com/posts/1");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + System.getenv("API_TOKEN"));
        int statusCode = connection.getResponseCode();
        assertEquals(200, statusCode, "Ожидался код 200 OK");
        connection.disconnect();
    }

    @Test
    public void testPostRequest() throws Exception {
        URL url = new URL("https://jsonplaceholder.typicode.com/posts");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + System.getenv("API_TOKEN"));
        connection.setDoOutput(true);

        String jsonBody = "{\"title\":\"foo\", \"body\":\"bar\", \"userId\":1}";

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int statusCode = connection.getResponseCode();
        assertEquals(201, statusCode, "Ожидался код 201 Created");
        connection.disconnect();
    }
}