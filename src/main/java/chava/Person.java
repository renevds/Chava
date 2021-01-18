package chava;

public class Person {

    private String ip;
    private String nickname;
    private String color = "#000000";

    public Person(String ip) {
        this.ip = ip;
        this.nickname = ip;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIp() {
        return ip;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

}
