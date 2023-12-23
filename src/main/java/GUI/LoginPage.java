package GUI;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import networking.Client;
import networking.packages.HelloPakcet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

import static GUI.GuiUtil.buttonScaleTransition;

public class LoginPage extends Scena {

    private static Stage stage;
    public static LoginPage instance;

    static {
        try {
            instance = new LoginPage();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public LoginPage() throws FileNotFoundException {
        super(root(), 800, 500);
        this.getStylesheets().add((Objects.requireNonNull(this.getClass().getResource("/login-page.css"))).toExternalForm());
        if (LoginPage.instance == null) {
            LoginPage.instance = this;
        }
    }


    private static Parent root() throws FileNotFoundException {
        HBox hBox = new HBox();

        Image image = new Image(new FileInputStream("/Users/dimimac/INTELLIJ/JAVA II/PROJEKAT/projekat-cs202/assets/logos/isumbg.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(500);
        imageView.setFitWidth(400);
        hBox.getChildren().addAll(imageView,root1());
        return hBox;
    }

    private static Parent root1() throws FileNotFoundException {


        AnchorPane root = new AnchorPane();

        root.setMinWidth(400);
        root.setMaxWidth(400);
        root.setMinHeight(500);
        root.setMaxHeight(500);
        Node logo = logo();
        Node loginBtn = loginButton();
        Node passField = password();
        Node userField = username();
        Node metLogoSlika = metSlika();

        root.getChildren().addAll(logo, loginBtn, passField, userField, metLogoSlika);

        AnchorPane.setTopAnchor(logo, 50d);
        AnchorPane.setLeftAnchor(logo, 100d);

        AnchorPane.setTopAnchor(loginBtn, 330.0);
        AnchorPane.setLeftAnchor(loginBtn, 100.0);

        AnchorPane.setLeftAnchor(passField, 75d);
        AnchorPane.setTopAnchor(passField, 250.0);

        AnchorPane.setLeftAnchor(userField, 75.0);
        AnchorPane.setTopAnchor(userField, 200.0);

        AnchorPane.setLeftAnchor(metLogoSlika, 150d);
        AnchorPane.setTopAnchor(metLogoSlika, 400d);

        root.getStyleClass().add("vbox-1");
        return root;
    }

    private static Node loginButton() throws FileNotFoundException {

        Button loginBtn = new Button();
        loginBtn.setId("loginBtn");
        buttonScaleTransition(loginBtn);
        loginBtn.setOnAction(actionEvent -> {
            Client.getInstance().send(new HelloPakcet());
            stage.setScene(Feed.instance2);
            GuiUtil.relocate(Feed.instance2);
        });
        loginBtn.getStyleClass().add("myButton");

        Label buttonText = new Label("Prijavi se");
        buttonText.setId("bt");
        HBox buttonBox = new HBox(buttonText, GuiUtil.createIcon("/Users/dimimac/INTELLIJ/JAVA II/PROJEKAT/projekat-cs202/assets/icons/person.png"));
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(15);
        loginBtn.setGraphic(buttonBox);
        return loginBtn;
    }



    private static Node password() {
        PasswordField passwordField = new PasswordField();
        passwordField.getStyleClass().add("pass-field");
        passwordField.setPromptText("Password");
        return passwordField;
    }

    private static Node username() {
        TextField usernameField = new TextField();
        usernameField.setId("usernameField");
        usernameField.setPromptText("Username");
        return usernameField;
    }

    private static Node logo() throws FileNotFoundException {
        Image image = new Image(new FileInputStream("/Users/dimimac/INTELLIJ/JAVA II/PROJEKAT/projekat-cs202/assets/logos/smet.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(200);
        return imageView;
    }
    /**
     * Generate a function comment for the given function body in a markdown code block with the correct language syntax.
     *
     * @return  the generated function comment
     * @throws FileNotFoundException  if the file is not found
     */

    private static Node metSlika() throws FileNotFoundException {
        Image image1 = new Image(new FileInputStream("/Users/dimimac/INTELLIJ/JAVA II/PROJEKAT/projekat-cs202/assets/logos/transp-met.png"));
        ImageView imageView1 = new ImageView(image1);
        imageView1.setFitWidth(100);
        imageView1.setFitHeight(100);
        return imageView1;
    }
    /**
     * Set the primary stage for the LoginPage.
     *
     * @param  stage  the Stage object to set as the primary stage
     */
    public static void setPrimaryStage(Stage stage) {
        LoginPage.stage = stage;
    }


}
