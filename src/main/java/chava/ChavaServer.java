package chava;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ChavaServer {

    private String ip;
    private int port;
    private HttpServer server;

    public ChavaServer(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    public void startServer() throws IOException {
        MessageController msgCtrl = new MessageController();
        Person testperson = new Person("test_ip");
        testperson.setNickname("tester");
        msgCtrl.addMessage(new Message("test", testperson));

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        server = HttpServer.create(new InetSocketAddress(ip, port), 0);
        server.createContext("/", new  ChavaHttpHandler(msgCtrl));
        server.setExecutor(threadPoolExecutor);
        server.start();
        System.out.println("Server started on " + ip + "@" + port);
    }
}
