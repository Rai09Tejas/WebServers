package WebServers.MultiThreadedServer;

public class Client {

    public Runnable getRunnable(){
        return new Runnable(){
            @Override
            public void run() {
                try {
                    int port = 8010;
                    java.net.InetAddress address = java.net.InetAddress.getByName("localhost");
                    java.net.Socket socket = new java.net.Socket(address, port);
                    java.io.PrintWriter toSocket = new java.io.PrintWriter(socket.getOutputStream(), true);
                    java.io.BufferedReader fromSocket = new java.io.BufferedReader(new java.io.InputStreamReader(socket.getInputStream()));
                    toSocket.println("Hello from the multi-threaded client!");
                    String response = fromSocket.readLine();
                    System.out.println("Response from server: " + response);
                    socket.close();
                    toSocket.close();
                    fromSocket.close();
                } catch (java.net.UnknownHostException e) {
                    e.printStackTrace();
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }
    public static void main(String[] args) {
        Client client = new Client();
        for(int i=0;i<100;i++){
            try{
                Thread thread = new Thread (client.getRunnable());
                thread.start();
            }catch(Exception ex){
                ex.printStackTrace();
                return;
            }
        }
    }
}
