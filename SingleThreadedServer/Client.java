package WebServers.SingleThreadedServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.UnknownHostException;

public class Client {
    public void run() throws UnknownHostException,IOException {
        int port = 8080;
        InetAddress address = InetAddress.getByName("localhost");
        Socket socket = new Socket(address, port);
        PrintWriter toSocket = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader fromSocket = new BufferedReader( new InputStreamReader( socket.getInputStream()));
        toSocket.println("Hello from the client!");
        String response = fromSocket.readLine();
        System.out.println("Response from server: "+response);
        socket.close();
        toSocket.close();
        fromSocket.close();
    }
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        try {
            client.run();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
