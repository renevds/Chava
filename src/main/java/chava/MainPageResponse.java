package chava;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class MainPageResponse {
    static void handleResponse(HttpExchange httpExchange, String requestParamValue) throws IOException {
        OutputStream outputStream = httpExchange.getResponseBody();
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html><head></head><style>label{color: white;} input, button{background-color: 404040 !important; color:white !important; border-color:black !important;} .list-group-item {background-color: 404040 !important; color:white}</style><body style=\"background-color:#121212; text-align: center\">" +
                "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1\" crossorigin=\"anonymous\">" +
                "<div style=\"text-align: center; background-color:#1DA1F2; color:white; padding: 0.5el; border-radius: 0.5em; width:70vw; margin: auto; font-size: 1.5em; margin-top:1em;\">").
        append("Welkom to the Eric lovers chat.").append("</div><img src ='https://i.imgur.com/zoO1jXn.png'><div style=\"width:500px; margin: auto\">").
        append("<form action=\"/profile\" method=\"post\" class=\"form-inline\">" +
                "<label for=\"name\">Name:</label><br>" +
                "<input type=\"text\" id=\"name\" class=\"form-control\" name=\"name\" maxlength=\"2000\" required><br>" +
                "<input type=\"color\" id=\"color\" name=\"color\" value=\"#000000\">" +
                "<input type=\"submit\" class=\"form-control\" value=\"Confirm\">" +
                " </form>");
        htmlBuilder.append(requestParamValue);
        htmlBuilder.append(
                "<form action=\"/\" method=\"post\" class=\"form-inline\">\n" +
                "  <label for=\"message\">Message:</label><br>\n" +
                "  <input type=\"text\" class=\"form-control\" id=\"message\" name=\"message\" maxlength=\"2000\"><br>\n" +
                "<input type=\"submit\" class=\"form-control\" value=\"Send\">" +
                "</form>" +
                "<button class=\"form-control\" onClick=\"window.location.reload();\">Refresh</button></div></html></body>");
        httpExchange.sendResponseHeaders(200, htmlBuilder.toString().length());
        outputStream.write(htmlBuilder.toString().getBytes());
        outputStream.flush();
        outputStream.close();

    }
}
