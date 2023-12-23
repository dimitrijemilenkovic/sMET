package networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Server {

    public static final int PORT = 8080;

    private static Server instance;
    private ServerSocket serverSocket;

    private boolean running = false;
    private boolean listening = false;

    private List<ServerClient> clients = new ArrayList<>();


    public Server() {
        if (instance == null) {
            instance = this;
        } else {
            throw new IllegalStateException("Server already exists");
        }
    }


    public static void main(String[] args) {

        new Server().start();
    }

    public void start() {

        if (running) {
            System.out.println("Server already running");
            return;
        }
        try {
            this.serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.running = true;
        System.out.println("Server started at port: " + PORT);
        acceptClients();
        while (this.running) {
            continue;
            //TODO: ovde logika ide
        }

    }

    public static Server getInstance() {
        return instance;
    }

    public void stop() {
        this.running = false;
        System.out.println("Server stopped");
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void acceptClients() {
        this.listening = true;
        System.out.println("Server started listening");
        new Thread(() -> {
            try {
                while (listening) {
                    Socket socket = serverSocket.accept();
                    ServerClient client = new ServerClient(socket);
                    clients.add(client);
                    new Thread(client).start();
                    System.out.println("New client connected : " + socket.getInetAddress());
                    if (clients.size() > 10) {
                        stopListening();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public boolean isListening() {
        return listening;
    }

    public void stopListening() {
        this.listening = false;
    }

    public void sendToAll(Object message) {
        for (ServerClient client : clients) {
            client.send(message);

        }

    }

    public void sendTo(Object message, ServerClient... clients) {

        for (ServerClient client : clients) {
            client.send(message);
        }


    }

    public void sendToAllExcept(ServerClient client, Object message) {
        for (ServerClient c : clients) {
            if (!Objects.equals(c, client)) {
                c.send(message);
            }
        }

    }

}
