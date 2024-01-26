package networking;

import networking.packages.ClientMessage;
import networking.packages.TestPacket;

public abstract class PackageHandler {
    protected static void handlePackageServer(Object message) {
        // How the server will handle packet
        if(message instanceof TestPacket packet){
            packet.someValue = 5;
            Server.send(new TestPacket(packet.someValue));
        }else if(message instanceof ClientMessage clientMessage){
            // TODO : Database.addNewMessage
            Server.send(new ClientMessage(clientMessage.message));
        }
    }

    protected static void handlePackageClient(Object message) {
        // How the client will handle packet
        if(message instanceof TestPacket packet){
            System.out.println("Exchanged packet with value: " + packet.someValue);
        }else if(message instanceof ClientMessage clientMessage){
            // TODO : Add message to queue
            // TODO : Update gui
        }
    }
}
