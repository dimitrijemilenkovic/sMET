package networking;
import networking.packages.ConnectRequest;
import org.mindrot.jbcrypt.*;
import java.sql.*;
public abstract class DatabaseUtil {
    private static boolean connected = false;
    private final static String URL = "jdbc:mysql://localhost:3306/iMET";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "1234";
    private static Connection connection = null;

    public static  void connect() {
        if (connected){
            System.out.println("Already connected");
            return;
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
        try {
            connection= DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return;
        }

        connected = true;

    }
    public static void disconnect() throws SQLException {
        connection.close();
        connected = false;
    }
    public static boolean isConnected() {
        return connected;
    }
    public static void test(){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from tabelaTeste;");
            while (resultSet.next()){
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addUser(String username, String password){

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        if (userExists(username)) return;
        String query = "INSERT INTO USERS (USERNAME, PASSWORD) VALUES ('" + username + "', '" + hashedPassword + "')";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean userExists(String username){
        String query = "SELECT * from USERS WHERE USERNAME = '" + username + "'";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean validateUser(ConnectRequest request) {
        String query = "SELECT * from USERS WHERE USERNAME = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, request.getUsername());
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) return false;
            String hashedPassword = resultSet.getString("PASSWORD");
            return BCrypt.checkpw(request.getPassword(), hashedPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
}
