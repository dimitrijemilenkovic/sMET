package networking;

import networking.packages.HelloPakcet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static final String HOST = "localhost";
    private static Client instance;
    private Socket socket;
    private boolean running = false;

    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;


    public Client() {
        if (instance == null) {
            instance = this;
        } else {
            throw new IllegalStateException("Client already exists");
        }

    }

    public static final Client getInstance() {
        return instance;
    }

    public void start() {
        if (this.running) {
            System.out.println("Client already running");
            return;
        }
        try {
            this.socket = new Socket(HOST, Server.PORT);
            this.objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
            this.objectInputStream = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            send(new HelloPakcet());
            while (running) {
                Object message = read();
                this.handleMessage(message);

            }

        }).start();

    }

    public void disconnect() {
        this.running = false;
        try {
            this.socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Object read() {
        try {
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            this.disconnect();
        }
        return null;
    }

    private void handleMessage(Object message) {

    }

    public void send(Object message) {
        try {
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
