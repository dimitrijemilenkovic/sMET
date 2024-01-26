package networking;

import networking.packages.ConnectRequest;
import networking.packages.DisconnectRequest;
import networking.packages.LogoutRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public abstract class Client {
    private static final String HOST = "localhost";

    private static boolean connected = false;

    public static boolean isConnected() {
        return connected;
    }

    private static boolean loggedIn = false;

    public static boolean isLoggedIn() {
        return loggedIn;
    }

    private static Socket socket = null;
    private static ObjectOutputStream out = null;
    private static ObjectInputStream in = null;

    public static void connect(String username, String password) throws IOException {
        if (isConnected()) {
            System.out.println("Can't connect to the server.Client is already connected!");
            logIn(username, password);
            return;
        }

        System.out.println("Connecting to the server...");
        socket = new Socket(HOST, Server.PORT);
        System.out.println("Connected to the server!");

        System.out.println("Initializing streams...");
        out = new ObjectOutputStream(socket.getOutputStream());
        out.flush();
        in = new ObjectInputStream(socket.getInputStream());
        System.out.println("Streams initialized!");

        connected = true;
        logIn(username, password);
    }

    private static void logIn(String username, String password) {
        if (loggedIn) {
            System.out.println("Can't log in. User is already logged in!");
            return;
        }
        System.out.println("Requesting login...");
        ConnectRequest request = new ConnectRequest(username, password);
        trySend(request);
        ConnectRequest response = (ConnectRequest) tryReceive();

        if (response == null || !response.isValidRequest()) {
            System.out.println("Login failed!");
            loggedIn = false;
            return;
        }
        System.out.println("Login successful!");
        loggedIn = true;

        listen();
    }

    private static void listen() {
        System.out.println("Listening for messages...");

        System.out.println("Starting new thread...");
        new Thread(() -> {
            try {
                socket.setSoTimeout(1000);
            } catch (SocketException e) {
                throw new RuntimeException(e);
            }
            while (isLoggedIn()) {
                Object message = null;
                try {
                    message = receive();
                } catch (SocketTimeoutException ex) {
                    if (!isLoggedIn()) {
                        break;
                    }
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                if (message != null) {
                    handleMessage(message);
                }
            }
        }).start();
        System.out.println("Thread started!");
    }

    private static void handleMessage(Object message) {
        if (message instanceof DisconnectRequest) {
            System.out.println("Received disconnect request!");

            forceDisconnect();

        }
        if (!loggedIn) {
            return;
        }
        PackageHandler.handlePackageClient(message);
    }


    private static void forceDisconnect() {
        System.out.println("Disconnecting...");
        try {
            if (!socket.isClosed()) {
                out.flush();
                out.close();
                in.close();
                socket.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        connected = false;
        System.out.println("Disconnected!");
    }

    public static void disconnect() throws IOException {
        System.out.println("Disconnecting...");
        System.out.println("Sending disconnect request...");
        trySend(new DisconnectRequest());
        if (!socket.isClosed()) {
            out.close();
            in.close();
            socket.close();
        }
        connected = false;
        System.out.println("Disconnected!");
    }

    public static void logout() {
        System.out.println("Logging out...");
        System.out.println("Sending logout request...");
        trySend(new LogoutRequest());
        loggedIn = false;
        System.out.println("Logged out!");
    }

    public static void send(Object message) throws IOException {
        out.writeObject(message);
        out.flush();
    }

    public static void trySend(Object message) {
        try {
            send(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object receive() throws IOException, ClassNotFoundException {
        return in.readObject();
    }

    public static Object tryReceive() {
        try {
            return receive();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}