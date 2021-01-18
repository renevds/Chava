package chava;

public class Person {

    private String ip;
    private String nickname = "";

    public Person(String ip) {
        this.ip = ip;
    }

    private String getNickname() {
        return nickname;
    }

    private void setNickname(String nickname) {
        this.nickname = nickname;
    }

    private String getIp() {
        return ip;
    }
}
