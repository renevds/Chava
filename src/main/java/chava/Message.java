package chava;

public class Message {

    protected String content;
    protected Person sender;

    public Message(String content, Person sender) {
        this.content = content;
        this.sender = sender;
    }

}
