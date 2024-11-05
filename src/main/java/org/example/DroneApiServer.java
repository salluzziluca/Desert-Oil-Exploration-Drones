package org.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.net.InetSocketAddress;

public class DroneApiServer {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8083), 0);
        server.createContext("/drones/process-command", new DroneHandler());
        server.setExecutor(null); // default executor
        server.start();
        System.out.println("Server started on port 8083...");
    }

    static class DroneHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                InputStream inputStream = exchange.getRequestBody();
                StringBuilder requestBody = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        requestBody.append(line);
                    }
                }

                String jsonInput = requestBody.toString();
                String input = jsonInput.substring(jsonInput.indexOf(":") + 2, jsonInput.lastIndexOf("\""));

                // Remove leading and trailing quotes if they exist
                input = input.replaceAll("^\"|\"$", "").trim();

                // Debug output to check the cleaned input
                System.out.println("Parsed input: " + input);

                // Split the cleaned input by actual newlines
                String[] commandArgs = input.split("\\\\n");  // Split using the literal "\n"

                // Trim each line to remove any leading/trailing spaces
                for (int i = 0; i < commandArgs.length; i++) {
                    commandArgs[i] = commandArgs[i].trim();
                }


                // Instantiate your controller and process the command
                Controller controller = new Controller(); // Adjust based on your actual controller instantiation
                String result;
                try {
                    result = controller.processInput(commandArgs);
                } catch (Exception e) {
                    // Set response headers and body for a 400 error
                    String errorResponse = "{ \"error\": \"" + e.getMessage().replace("\"", "\\\"") + "\" }";
                    exchange.getResponseHeaders().set("Content-Type", "application/json");
                    exchange.sendResponseHeaders(400, errorResponse.length());
                    try (OutputStream outputStream = exchange.getResponseBody()) {
                        outputStream.write(errorResponse.getBytes());
                    }
                    return;
                }

                // Create JSON response manually
                String jsonResponse = "{ \"result\": \"" + result.replace("\"", "\\\"") + "\" }";

                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, jsonResponse.length());
                try (OutputStream outputStream = exchange.getResponseBody()) {
                    outputStream.write(jsonResponse.getBytes());
                }
            } else {
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            }
        }

    }
}
