package com.javalearning.ai;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Collectors;

@Component
public class Aistudio {

    static final String APIKEY =
            "AIzaSyDbx419lKWluGfaF6HcHD_ZD-0PaY3GXDI";
    static final String PAYLOAD = """
            {
                "contents": [
                  {
                    "parts": [
                      {
                        "text": "PAYLOAD"
                      }
                    ]
                  }
                ]
              }
            """;

    static HttpRequest.Builder requestBuilder = null;

    static {
        requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=GEMINI_API_KEY"
                        .replace("GEMINI_API_KEY", APIKEY)))
                .header("content-type", "application/json");
    }

    public static void main(String[] args) throws IOException, InterruptedException {


        HttpClient httpClient = HttpClient.newHttpClient();
//        HttpResponse<InputStream> send = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
        InputStream body = Aistudio.class.getClassLoader().getResourceAsStream("aistudio.json");
//        InputStream body = send.body();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(body));
        String collect = bufferedReader.lines().collect(Collectors.joining("\n"));
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(collect, JsonObject.class);
        JsonElement candidates = jsonObject.get("candidates");
        JsonObject candidatesObj = candidates.getAsJsonArray().get(0).getAsJsonObject();
        JsonElement jsonElement = candidatesObj.get("content").getAsJsonObject().get("parts").getAsJsonArray().get(0).getAsJsonObject().get("text");
        System.out.println(jsonElement.getAsString());
    }

    public String getGeminiResponse(String content) {
        String response = "";
        HttpRequest payload = requestBuilder
                .POST(HttpRequest.BodyPublishers.ofString(PAYLOAD.replace("PAYLOAD", content)))
                .build();
        HttpClient client = HttpClient.newBuilder().build();
        try {
            HttpResponse<InputStream> send = client.send(payload, HttpResponse.BodyHandlers.ofInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(send.body()));
            String collect = bufferedReader.lines().collect(Collectors.joining("\n"));
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(collect, JsonObject.class);
            System.out.println(jsonObject.toString());
            JsonElement candidates = jsonObject.get("candidates");
            JsonObject candidatesObj = candidates.getAsJsonArray().get(0).getAsJsonObject();
            JsonElement jsonElement = candidatesObj.get("content").getAsJsonObject().get("parts").getAsJsonArray().get(0).getAsJsonObject().get("text");
            response = jsonElement.getAsString();
        } catch (IOException e) {
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return response;
    }
}
