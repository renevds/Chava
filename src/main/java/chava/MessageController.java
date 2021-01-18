package chava;

import org.apache.commons.text.StringEscapeUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MessageController {

    private List<Message> messages = new ArrayList<>();
    private List<Person> persons = new ArrayList<>();

    public MessageController() {
    }

    public void addMessage(Message message) {
        if(! persons.contains(message.getSender())) {
            persons.add(message.getSender());
        }
        messages.add(message);
    }

    public List<Message> getMessages() {
        return messages;
    }

    public String displayMessages() {
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<ul style='font-size: 1.5em'>");
        for(Message message: messages) {
            htmlBuilder.append("<li>").append(message.getSender().getNickname()).append(": ").append(message.getContent()).append("<l/i>");
        }
        htmlBuilder.append("</ul>");
        return htmlBuilder.toString();
    }

    public List<Person> getSenders() {
        return persons;
    }

    public Person getSender(String ip) {
        Person person = null;
        Iterator<Person> iterator = persons.iterator();
        while(person == null && iterator.hasNext()) {
            Person nextPerson = iterator.next();
            if(nextPerson.getIp().equals(ip)) {
                person = nextPerson;
            }
        }
        if(person == null) {
            System.out.println("new");
            person = new Person(ip);
            persons.add(person);
        }
        return person;
    }

    public Message getLastMessage(String ip) {
        Message lastMessage = null;
        for(int i=messages.size() - 1; i >= 0 && lastMessage == null; i--) {
            if(messages.get(i).getSender().getIp().equals(ip)) {
                lastMessage = messages.get(i);
            }
        }
        return lastMessage;
    }

    public Message getLastMessage(Person person) {
        return getLastMessage(person.getIp());
    }
}
