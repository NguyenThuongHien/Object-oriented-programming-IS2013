package lab9;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer implements Runnable {

    private static final ExecutorService ex = Executors.newFixedThreadPool(1);

    public static void acceptConnections() {
        try {
            ServerSocket server = new ServerSocket(1666);
            Socket incomingConnection = null;
            while (true) {
                incomingConnection = server.accept();
                handleConnection(incomingConnection);
            }
        } catch (BindException e) {
            System.out.println("Unable to bind to port 1666");
        } catch (IOException e) {
            System.out
                    .println("Unable to instantiate a ServerSocket on port: 1666");
        }
    }

    public static void handleConnection(Socket connectionToHandle) {
        ex.execute(new EchoServer(connectionToHandle));
    }

    Socket socketToHandle;

    public EchoServer(Socket aSocketToHandle) {
        socketToHandle = aSocketToHandle;
    }

    @Override
    public void run() {
        try {
            BufferedReader in;
            try (PrintWriter out = new PrintWriter(socketToHandle.getOutputStream(),
                    true)) {
                in = new BufferedReader(new InputStreamReader(
                        socketToHandle.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    out.println(inputLine);
                }
            }
            in.close();
            socketToHandle.close();
        } catch (IOException e) {
        }
    }

    public static void main(String[] args) throws IOException {
        acceptConnections();
    }
}
