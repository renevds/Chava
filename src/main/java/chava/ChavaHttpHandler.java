package chava;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.text.StringEscapeUtils;

public class ChavaHttpHandler implements HttpHandler {

    private MessageController msgCtrl;

    public ChavaHttpHandler(MessageController msgCtr){
        this.msgCtrl = msgCtr;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println(httpExchange.getRemoteAddress() + " connected. (" + httpExchange.getRequestMethod() + ")");
        if ("GET".equals(httpExchange.getRequestMethod())) {
            handleResponse(httpExchange, msgCtrl.displayMessages());
        } else if ("POST".equals(httpExchange.getRequestMethod())) {
            handlePostRequest(httpExchange);
        }

    }

    private void handlePostRequest(HttpExchange httpExchange) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStream ios = httpExchange.getRequestBody();
        int i;
        while ((i = ios.read()) != -1) {
            sb.append((char) i);
        }

        String messageString = sb.toString().substring(8);



        System.out.println("hm: " + messageString);
        handleResponse(httpExchange, msgCtrl.displayMessages());
    }

    private void handleResponse(HttpExchange httpExchange, String requestParamValue)  throws  IOException {
        OutputStream outputStream = httpExchange.getResponseBody();
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<h1>Welkom ").append(httpExchange.getRemoteAddress().toString()).append("</h1>");
        htmlBuilder.append(requestParamValue);
        htmlBuilder.append("<form action=\"\" method=\"post\">\n" +
                "  <label for=\"message\">Message:</label><br>\n" +
                "  <input type=\"text\" id=\"message\" name=\"message\"><br>\n" +
                "<input type=\"submit\" value=\"Submit\">" +
                "</form>");
        httpExchange.sendResponseHeaders(200, htmlBuilder.toString().length());
        outputStream.write(htmlBuilder.toString().getBytes());
        outputStream.flush();
        outputStream.close();
    }



}
