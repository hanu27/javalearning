package com.javalearning.ai;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.stream.Collectors;

public class Aistudio {
    public static void main(String[] args) throws IOException, InterruptedException {
        String apikey =
                "AIzaSyDbx419lKWluGfaF6HcHD_ZD-0PaY3GXDI";
        String payload = """
                {
                    "contents": [
                      {
                        "parts": [
                          {
                            "text": "Explain how AI works in a few words"
                          }
                        ]
                      }
                    ]
                  }
                """;

        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=GEMINI_API_KEY"
                        .replace("GEMINI_API_KEY", apikey)))
                .header("content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
//        HttpResponse<InputStream> send = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
        InputStream body = Aistudio.class.getClassLoader().getResourceAsStream("aistudio.txt");
//        InputStream body = send.body();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(body));
        String collect = bufferedReader.lines().collect(Collectors.joining("\n"));
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(collect, JsonObject.class);
        System.out.println(collect);
    }
}
