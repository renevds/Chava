package chava;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.text.StringEscapeUtils;



public class ChavaHttpMessageHandler implements HttpHandler {

    private MessageController msgCtrl;

    public ChavaHttpMessageHandler(MessageController msgCtr){
        this.msgCtrl = msgCtr;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println(httpExchange.getRemoteAddress().toString().split(":")[0] + " connected. (" + httpExchange.getRequestMethod() + ")");
        if ("GET".equals(httpExchange.getRequestMethod())) {
            MainPageResponse.handleResponse(httpExchange, msgCtrl.displayMessages());
        } else if ("POST".equals(httpExchange.getRequestMethod())) {
            handlePostRequest(httpExchange);
        }

    }

    private void handlePostRequest(HttpExchange httpExchange) throws IOException {
        StringBuilder text = new StringBuilder();
        InputStream ios = httpExchange.getRequestBody();
        int i;
        while ((i = ios.read()) != -1) {
            text.append((char) i);
        }


        String sb = text.toString().substring(8);


        String messageString = StringEscapeUtils.escapeHtml4(java.net.URLDecoder.decode(sb, StandardCharsets.UTF_8.name()));

        Message newMes = new Message(messageString, msgCtrl.getSender(httpExchange.getRemoteAddress().toString().split(":")[0]));

        Message check = msgCtrl.getLastMessage(httpExchange.getRemoteAddress().toString().split(":")[0]);

        if((check == null || !check.getContent().equals(messageString)) && messageString != ""){
            msgCtrl.addMessage(newMes);
            System.out.println("hm: " + messageString);
        }

        MainPageResponse.handleResponse(httpExchange, msgCtrl.displayMessages());
    }

}
