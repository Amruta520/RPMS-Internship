package main;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import dao.CandidateDAO;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class RPSSserver {

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(
            new InetSocketAddress(8080), 0
        );

        server.createContext("/api/candidates", exchange -> {

            addCors(exchange);

            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                exchange.close();
                return;
            }

            try {

                CandidateDAO dao = new CandidateDAO();

                if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {

                    String json = toJson(dao.getAllCandidates());

                    sendResponse(exchange, 200, json);

                } else if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {

                    String body = readBody(exchange);

                    Map<String, String> data = parseForm(body);

                    String code = dao.addCandidate(data);

                    String response =
                        "{\"success\":true," +
                        "\"message\":\"Candidate saved successfully\"," +
                        "\"candidate_code\":\"" + escape(code) + "\"}";

                    sendResponse(exchange, 201, response);

                } else {

                    sendResponse(
                        exchange,
                        405,
                        "{\"success\":false,\"error\":\"Method not allowed\"}"
                    );
                }

            } catch (IllegalArgumentException e) {

                sendResponse(
                    exchange,
                    400,
                    "{\"success\":false,\"error\":\"" +
                    escape(e.getMessage()) + "\"}"
                );

            } catch (Exception e) {

                e.printStackTrace();

                String message = e.getMessage() == null
                    ? "Server error" : e.getMessage();

                sendResponse(
                    exchange,
                    500,
                    "{\"success\":false,\"error\":\"" +
                    escape(message) + "\"}"
                );
            }
        });

        server.start();

        System.out.println("RPMS Server Started!");
        System.out.println("Candidates API: http://127.0.0.1:8080/api/candidates");
        System.out.println("GET and POST are enabled.");
    }

    private static void addCors(HttpExchange exchange) {

        exchange.getResponseHeaders().set(
            "Access-Control-Allow-Origin", "*"
        );

        exchange.getResponseHeaders().set(
            "Access-Control-Allow-Methods",
            "GET, POST, OPTIONS"
        );

        exchange.getResponseHeaders().set(
            "Access-Control-Allow-Headers", "Content-Type"
        );
    }

    private static String readBody(HttpExchange exchange)
        throws IOException {

        ByteArrayOutputStream output = new ByteArrayOutputStream();

        byte[] buffer = new byte[4096];

        int n;

        try (InputStream input = exchange.getRequestBody()) {

            while ((n = input.read(buffer)) != -1) {
                output.write(buffer, 0, n);
            }
        }

        return output.toString(StandardCharsets.UTF_8);
    }

    private static Map<String, String> parseForm(String body)
        throws Exception {

        Map<String, String> data = new HashMap<>();

        if (body == null || body.isEmpty()) {
            return data;
        }

        for (String pair : body.split("&")) {

            String[] parts = pair.split("=", 2);

            String key = URLDecoder.decode(parts[0], "UTF-8");

            String value = parts.length > 1
                ? URLDecoder.decode(parts[1], "UTF-8") : "";

            data.put(key, value);
        }

        return data;
    }

    private static String toJson(List<Map<String, Object>> list) {

        StringBuilder json = new StringBuilder("[");

        boolean first = true;

        for (Map<String, Object> row : list) {

            if (!first) {
                json.append(",");
            }

            first = false;

            json.append("{");

            boolean firstField = true;

            for (String key : row.keySet()) {

                if (!firstField) {
                    json.append(",");
                }

                firstField = false;

                json.append("\"").append(escape(key)).append("\":");

                Object value = row.get(key);

                if (value == null) {
                    json.append("null");

                } else if (value instanceof Number ||
                           value instanceof Boolean) {

                    json.append(value);

                } else {

                    json.append("\"")
                        .append(escape(value.toString()))
                        .append("\"");
                }
            }

            json.append("}");
        }

        json.append("]");

        return json.toString();
    }

    private static String escape(String value) {

        return value.replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r");
    }

    private static void sendResponse(
        HttpExchange exchange,
        int status,
        String response
    ) throws IOException {

        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders().set(
            "Content-Type",
            "application/json; charset=UTF-8"
        );

        exchange.sendResponseHeaders(status, bytes.length);

        try (OutputStream output = exchange.getResponseBody()) {
            output.write(bytes);
        }
    }
}