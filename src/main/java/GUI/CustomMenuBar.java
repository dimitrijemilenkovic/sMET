package GUI;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import networking.Client;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static GUI.Scena.screen;

public class CustomMenuBar {
    private static Stage stage;
    private static final Rectangle2D bounds = screen.getVisualBounds();


    private final AnchorPane anchorPane;


    public CustomMenuBar(String feedButtonStyle, String profileButtonStyle, String messagesButtonStyle, String eventButtonStyle, String iMetButtonStyle) throws FileNotFoundException {
        anchorPane = new AnchorPane();

        String feedString = "  Feed";
        String profileString = " Profile";
        String eventString = " Events";
        String iMetString = " iMet";
        String logOutString = " Log out";
        String messangerString = " Messages";

        String feedIconPath = "/Users/dimimac/INTELLIJ/JAVA II/PROJEKAT/projekat-cs202/assets/buttonImages/home.png";
        String profileIconPath = "/Users/dimimac/INTELLIJ/JAVA II/PROJEKAT/projekat-cs202/assets/buttonImages/user.png";
        String eventIconPath = "/Users/dimimac/INTELLIJ/JAVA II/PROJEKAT/projekat-cs202/assets/buttonImages/event.png";
        String metIconPath = "/Users/dimimac/INTELLIJ/JAVA II/PROJEKAT/projekat-cs202/assets/buttonImages/met.png";
        String logoutIconPath = "/Users/dimimac/INTELLIJ/JAVA II/PROJEKAT/projekat-cs202/assets/buttonImages/logoff.png";
        String messagesIconPath = "/Users/dimimac/INTELLIJ/JAVA II/PROJEKAT/projekat-cs202/assets/buttonImages/messages.png";


        Node logo = GuiUtil.logo();
        Node feedBtn = createButton(feedString, feedButtonStyle, feedIconPath, () -> switchScene(Feed.instance2));

        Node messangerBtn = createButton(messangerString, messagesButtonStyle, messagesIconPath, () -> switchScene(MessangerPage.instance));

        Node profileBtn = createButton(profileString, profileButtonStyle, profileIconPath, () -> switchScene(ProfilePage.instance));
        profileBtn.setId(profileButtonStyle);

        Node eventBtn = createButton(eventString, eventButtonStyle, eventIconPath, () -> switchScene(Feed.instance2));
        eventBtn.setId(eventButtonStyle);

        Node iMetButton = createButton(iMetString, iMetButtonStyle, metIconPath, this::openURL);
        iMetButton.setId(iMetButtonStyle);

        Node logoutBtn = createButton(logOutString, "inactive-button-menu-bar", logoutIconPath, () -> {
            Client.logout();
            switchScene(LoginPage.instance);

        });
        logoutBtn.setId("inactive-button-menu-bar");


        anchorPane.getChildren().addAll(logo, feedBtn, profileBtn, messangerBtn, eventBtn, iMetButton, logoutBtn);


        AnchorPane.setTopAnchor(logo, 0d);
        AnchorPane.setLeftAnchor(logo, 50d);

        AnchorPane.setRightAnchor(feedBtn, 0d);
        AnchorPane.setTopAnchor(feedBtn, 150d);

        AnchorPane.setTopAnchor(profileBtn, 250d);
        AnchorPane.setRightAnchor(profileBtn, 0d);

        AnchorPane.setTopAnchor(messangerBtn, 350d);
        AnchorPane.setRightAnchor(messangerBtn, 0d);

        AnchorPane.setTopAnchor(eventBtn, 450d);
        AnchorPane.setRightAnchor(eventBtn, 0d);

        AnchorPane.setTopAnchor(iMetButton, 550d);
        AnchorPane.setRightAnchor(iMetButton, 0d);

        AnchorPane.setTopAnchor(logoutBtn, 650d);
        AnchorPane.setRightAnchor(logoutBtn, 0d);

        anchorPane.setMinWidth(bounds.getWidth() * 0.1875);
        anchorPane.setMaxWidth(bounds.getWidth() * 0.1875);

        anchorPane.getStyleClass().add("menu-bar");
    }

    public static void setPrimaryStage(Stage stage) {
        CustomMenuBar.stage = stage;
    }

    public AnchorPane getCustomMenuBar() {
        return anchorPane;
    }

    private void switchScene(Scene scene) {
        stage.setScene(scene);
        GuiUtil.relocate(scene);
    }

    private void openURL() {
        try {
            Desktop.getDesktop().browse(new URI("https://imet.metropolitan.ac.rs/student/#/home"));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private Node createButton(String buttonText, String id, String iconPath, Runnable action) throws FileNotFoundException {
        Button button = GuiUtil.createButtonMenu(buttonText, id, iconPath);
        button.setOnAction(actionEvent -> {
            action.run();
        });

        GuiUtil.buttonScaleTransitionMenu(button);
        return button;
    }


}

