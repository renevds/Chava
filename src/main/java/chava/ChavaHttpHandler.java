package chava;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.text.StringEscapeUtils;

public class ChavaHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println(httpExchange.getRemoteAddress() + " connected. (" + httpExchange.getRequestMethod() + ")");
        String requestParamValue = null;
        if ("GET".equals(httpExchange.getRequestMethod())) {
            handleResponse(httpExchange, "");
        } else if ("POST".equals(httpExchange)) {
            handlePostRequest(httpExchange);
        }

    }

    private String handlePostRequest(HttpExchange httpExchange) {
        System.out.println(httpExchange.getRequestBody().toString());
        return httpExchange.getRequestBody().toString();
    }

    private void handleResponse(HttpExchange httpExchange, String requestParamValue)  throws  IOException {
        OutputStream outputStream = httpExchange.getResponseBody();
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<h1>Welkom ").append(httpExchange.getRemoteAddress().toString()).append("</h1>");
        httpExchange.sendResponseHeaders(200, htmlBuilder.toString().length());
        outputStream.write(htmlBuilder.toString().getBytes());
        outputStream.flush();
        outputStream.close();
    }

    private String handleGetRequest(HttpExchange httpExchange) {
        return httpExchange.getRequestURI().toString().split("\\?")[1].split("=")[1];
    }


}
