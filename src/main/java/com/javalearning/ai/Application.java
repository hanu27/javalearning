package com.javalearning.ai;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {
        String apikey =
                "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";
        String payload = """
                {
                 "model" : "gpt-4o",
                 "messages":[
                    {
                        "role":"user",
                        "content":"what is the your view of AI, it going to replace jobs"                    
                    }
                 ]                
                }
                """;

        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/chat/completions"))
                .header("content-type","application/json")
                .header("Authorization","Bearer "+ apikey)
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> send = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(send);
    }
}
