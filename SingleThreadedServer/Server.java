package WebServers.SingleThreadedServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public void run() throws IOException {
        int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);
        while(true){
            try {
                System.out.println("Server listening on port " + port + "...");
                Socket acceptedConnection = serverSocket.accept();
                System.out.println("Connection accepted from : "+acceptedConnection.getRemoteSocketAddress());
                PrintWriter out = new PrintWriter(acceptedConnection.getOutputStream(), true);     //toClient
                BufferedReader in = new BufferedReader( new InputStreamReader( acceptedConnection.getInputStream()));  //fromClient
                String clientMessage = in.readLine();
                out.println("Hello from the server!");
                System.out.println("Message from client: " + clientMessage);
                serverSocket.close();
                out.close();
                in.close();
                acceptedConnection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        
    }
    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
