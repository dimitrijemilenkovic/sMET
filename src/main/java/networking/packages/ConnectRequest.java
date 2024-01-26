package networking.packages;

import java.io.Serializable;

public class ConnectRequest implements Serializable {
    private final String username,password;

    public ConnectRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private boolean validRequest = false;

    public final boolean isValidRequest(){
            return this.validRequest;
    }

    public void setValidRequest(boolean validRequest){
        this.validRequest = validRequest;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
