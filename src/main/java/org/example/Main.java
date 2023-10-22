package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private final static String url = "https://dummyjson.com/quotes/random";
    private final static ObjectMapper om = new ObjectMapper();;
    private final static Scanner scanner = new Scanner(System.in);
    private static final List<Quote> quoteList = new ArrayList<>();;

    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        HttpClient httpClient = createHttpClient();
        HttpRequest httpRequest = createHttpRequest();
        printEmptyLines();
        System.out.println("Welcome to our quotes app!");
        while (true) {
            System.out.println(" ");
            System.out.println("Do you want to get a random quote?");
            System.out.print("Type YES or NO: ");
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("NO")) {
                showAllQuotes();
                break;
            } else if (!userInput.equalsIgnoreCase("YES")) {
                System.out.println("Awww.. sorry we do not understand...");
                System.out.println(" ");
                continue;
            }
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            Quote quote = buildQuoteFromJsonNode(om.readTree(response.body()));
            quote.show();
            quoteList.add(quote);
        }
    }

    private static HttpClient createHttpClient() {
        return HttpClient.newBuilder().build();
    }

    private static HttpRequest createHttpRequest() throws URISyntaxException {
        URI uri = new URI(url);
        return HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
    }

    private static Quote buildQuoteFromJsonNode(JsonNode quoteNode) {
        return new Quote(quoteNode.get("author").asText(), quoteNode.get("quote").asText());
    }

    private static void showAllQuotes() {
        if (quoteList.size() == 0) {
            return;
        }
        System.out.println("Before you go we would like to show all quotes you have been generated.");
        System.out.println(" ");
        for (int i = 0; i < quoteList.size(); i++) {
            int number = i + 1;
            System.out.println("Quote number: " + number);
            quoteList.get(i).show();
        }
        System.out.println("In total you had generated: " + quoteList.size() + " quotes." );
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
    }

    private static void printEmptyLines(){
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
    }
}