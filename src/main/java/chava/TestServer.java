package chava;

import java.io.IOException;

public class TestServer {
    public static void main(String[] args) throws IOException {
        ChavaServer server = new ChavaServer("192.168.0.123",8000);
        server.startServer();
    }
}
