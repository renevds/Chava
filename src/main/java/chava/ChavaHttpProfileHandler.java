package chava;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.commons.text.StringEscapeUtils;

public class ChavaHttpProfileHandler implements HttpHandler {
    private MessageController msgCtrl;

    public ChavaHttpProfileHandler(MessageController msgCtrl){
        this.msgCtrl = msgCtrl;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println(httpExchange.getRemoteAddress().toString().split(":")[0] + " connected. (" + httpExchange.getRequestMethod() + ")");
        if ("POST".equals(httpExchange.getRequestMethod())) {
            handlePostRequest(httpExchange);
        }

    }

    private void handlePostRequest(HttpExchange httpExchange) throws IOException {

        String codedText = IOUtils.toString(httpExchange.getRequestBody(), StandardCharsets.UTF_8.name());
        String text = java.net.URLDecoder.decode(codedText, StandardCharsets.UTF_8.name());

        System.out.println("profile: " + text);

        String messageString = StringEscapeUtils.escapeHtml4(text.toString().split("&")[0].split("=")[1]);
        String messageColor = StringEscapeUtils.escapeHtml4(text.toString().split("&")[1].split("=")[1]);

        System.out.println("profile name: " + messageString);
        System.out.println("profile color: " + messageColor);
        Person per = msgCtrl.getSender(httpExchange.getRemoteAddress().toString().split(":")[0]);
        per.setNickname(messageString);
        per.setColor(messageColor);
        MainPageResponse.handleResponse(httpExchange, msgCtrl.displayMessages());

    }
}
