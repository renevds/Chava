package chava;

import org.apache.commons.text.StringEscapeUtils;

import java.util.ArrayList;
import java.util.List;

public class MessageController {

    private List<Message> messages = new ArrayList<>();

    public MessageController() {
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public List<Message> getMessages() {
        return messages;
    }

    public String displayMessages() {
        StringBuilder htmlBuilder = new StringBuilder();

        for(Message message: messages) {
            htmlBuilder.append("<h2>").append(message.getSender().getNickname()).append("</h2>").append("<p>").append(message.getContent()).append("</p>");
        }
        return htmlBuilder.toString();
    }
}
