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
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        server = HttpServer.create(new InetSocketAddress(ip, port), 0);
        server.createContext("/", new  ChavaHttpHandler());
        server.setExecutor(threadPoolExecutor);
        server.start();
        System.out.println("Server started on " + ip + "@" + port);
    }
}
