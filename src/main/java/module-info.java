module com.example.projekatcs202 {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.j;
    requires jbcrypt;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires java.desktop;
    requires java.sql;

    opens com.example.projekatcs202 to javafx.fxml;
    exports com.example.projekatcs202;
    exports GUI;
    opens GUI to javafx.fxml;


}