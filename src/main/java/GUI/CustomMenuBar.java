package GUI;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import static GUI.Scena.screen;

public class CustomMenuBar {
    private static Stage stage;
    private static final Rectangle2D bounds = screen.getVisualBounds();

    private String buttonText;


    private final AnchorPane anchorPane;


    public CustomMenuBar(String feedButtonStyle, String profileButtonStyle,String eventButtonStyle, String iMetButtonStyle) throws FileNotFoundException {
        anchorPane = new AnchorPane();

        String feedString = "F E E D";
        String profileString = "P R O F I L E";
        String eventString = "E V E N T";
        String iMetString = "I M E T";
        String logOutString = "L O G  O U T";


        Node logo = GuiUtil.logo();
        Node feedBtn = createButton(feedString,feedButtonStyle ,() -> switchScene(Feed.instance2));

        Node profileBtn = createButton(profileString, profileButtonStyle,() -> switchScene(ProfilePage.instance));
        profileBtn.setId(profileButtonStyle);

        Node eventBtn = createButton(eventString, eventButtonStyle, () -> switchScene(Feed.instance2));
        eventBtn.setId(eventButtonStyle);

        Node iMetButton = createButton(iMetString, iMetButtonStyle, () -> openURL("https://imet.metropolitan.ac.rs/student/#/home"));
        iMetButton.setId(iMetButtonStyle);

        Node logoutBtn = createButton(logOutString, "inactive-button-menu-bar", () -> switchScene(LoginPage.instance));
        logoutBtn.setId("inactive-button-menu-bar");


        anchorPane.getChildren().addAll(logo, feedBtn, profileBtn, eventBtn, iMetButton, logoutBtn);



        AnchorPane.setTopAnchor(logo, 0d);
        AnchorPane.setLeftAnchor(logo, 0d);

        AnchorPane.setRightAnchor(feedBtn, 0d);
        AnchorPane.setTopAnchor(feedBtn, 150d);

        AnchorPane.setTopAnchor(profileBtn, 250d);
        AnchorPane.setRightAnchor(profileBtn, 0d);

        AnchorPane.setTopAnchor(eventBtn, 350d);
        AnchorPane.setRightAnchor(eventBtn, 0d);

        AnchorPane.setTopAnchor(iMetButton, 450d);
        AnchorPane.setRightAnchor(iMetButton, 0d);

        AnchorPane.setTopAnchor(logoutBtn, 550d);
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

    private void openURL(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private Node createButton(String buttonText,String id, Runnable action) {
        Button button = GuiUtil.createButtonMenu(buttonText,id);
        button.setOnAction(actionEvent -> {
            action.run();
        });

        GuiUtil.buttonScaleTransitionMenu(button);
        return button;
    }



}

