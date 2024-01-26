package networking.packages;

import java.io.Serializable;

public class ClientMessage implements Serializable {
    public final String message;

    public ClientMessage(String message) {
        this.message = "Username: "+ message;
    }
}