package WebServers.MultiThreadedServer;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server {
    public Consumer<Socket> getConsumer() {
        return (clientSocket)-> {
            try {
                PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true);
                toClient.println("Hello from Multi-threaded server!");
                toClient.close();
                clientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    public void run() {
        // Implementation for multi-threaded server goes here
        int port = 8010;
        Server server =new Server();
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(10000);
            System.out.println("Multi-threaded Server listening on port " + port + "...");
            while (true) {
                try {
                    Socket acceptedSocket = serverSocket.accept();
                    System.out.println("Connection accepted from : " + acceptedSocket.getRemoteSocketAddress());
                    Thread thread = new Thread(() -> server.getConsumer().accept(acceptedSocket));
                    thread.start();
                    serverSocket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.run();
    }
}
