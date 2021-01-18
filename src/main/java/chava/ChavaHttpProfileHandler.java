package chava;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;

public class ChavaHttpProfileHandler implements HttpHandler {
    private MessageController msgCtrl;

    public ChavaHttpProfileHandler(MessageController msgCtrl){
        this.msgCtrl = msgCtrl;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println(httpExchange.getRemoteAddress() + " connected. (" + httpExchange.getRequestMethod() + ")");
        if ("POST".equals(httpExchange.getRequestMethod())) {
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


        String messageString = sb.substring(5);

        System.out.println("profile: " + messageString);
        msgCtrl.getSender(httpExchange.getRemoteAddress().toString()).setNickname(messageString);
        MainPageResponse.handleResponse(httpExchange, msgCtrl.displayMessages());

    }
}
