package chava;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class MainPageResponse {
    static void handleResponse(HttpExchange httpExchange, String requestParamValue) throws IOException {
        OutputStream outputStream = httpExchange.getResponseBody();
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<h1>Welkom ").
        append(httpExchange.getRemoteAddress().toString()).append("</h1>").
        append("<form action=\"/profile\" method=\"post\">" +
                "<label for=\"name\">Name:</label><br>" +
                "<input type=\"text\" id=\"name\" name=\"name\"><br>" +
                "<input type=\"submit\" value=\"Confirm\">" +
                " </form>");
        htmlBuilder.append(requestParamValue);
        htmlBuilder.append(
                "<form action=\"/\" method=\"post\">\n" +
                "  <label for=\"message\">Message:</label><br>\n" +
                "  <input type=\"text\" id=\"message\" name=\"message\"><br>\n" +
                "<input type=\"submit\" value=\"Send\">" +
                "</form>" +
                "<button onClick=\"window.location.reload();\">Refresh</button>");
        httpExchange.sendResponseHeaders(200, htmlBuilder.toString().length());
        outputStream.write(htmlBuilder.toString().getBytes());
        outputStream.flush();
        outputStream.close();

    }
}
