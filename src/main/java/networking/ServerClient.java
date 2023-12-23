package networking;

import networking.packages.HelloPakcet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerClient implements Runnable {

    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private Socket socket;


    public ServerClient(Socket socket) {
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
                Object message = objectInputStream.readObject();
                this.handleMessage(message);
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

    private void handleMessage(Object message) {
        if (message instanceof HelloPakcet) {
            System.out.println("Received hello packet from client: " + socket.getInetAddress());
        }
    }
}
