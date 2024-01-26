package networking;

import networking.packages.ConnectRequest;
import networking.packages.DisconnectRequest;
import networking.packages.LogoutRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        if (this.socket != null && !this.socket.isClosed()) {
            try {
                objectInputStream = new ObjectInputStream(this.socket.getInputStream());
                objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else
            throw new RuntimeException("Socket is null or closed");
    }

    @Override
    public void run() {
        try {
            while (true) {
                Object message = receive();
                if (!this.handleMessage(message))
                    break;
            }

        } catch (IOException | ClassNotFoundException e) {
            try {
                objectOutputStream.close();
                objectInputStream.close();
                this.socket.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void send(Object message) {
        try {
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Object receive() throws IOException, ClassNotFoundException {
        return objectInputStream.readObject();
    }

    protected void disconnect() {
        try {
            this.objectInputStream.close();
            this.objectOutputStream.close();
            this.socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean loggedIn = false;

    private boolean handleMessage(Object message) {
        if (message instanceof DisconnectRequest) {
            this.disconnect();
            Server.removeClient(this);
            return false;
        } else if (message instanceof LogoutRequest) {
            loggedIn = false;
        } else if (message instanceof ConnectRequest request) {
            boolean valid = DatabaseUtil.validateUser(request);
            request.setValidRequest(valid);
            send(request);
            loggedIn = valid;
        }
        if (!loggedIn) {
            return true;
        }
        PackageHandler.handlePackageServer(message);
        return true;
    }
}
