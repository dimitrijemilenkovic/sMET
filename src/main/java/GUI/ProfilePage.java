package GUI;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public class ProfilePage extends Scena {
    private static Stage stage;
    public static ProfilePage instance;
    private static final Rectangle2D bounds = screen.getVisualBounds();
    static {
        try {
            instance = new ProfilePage();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ProfilePage() throws FileNotFoundException {
        super(root());
        this.getStylesheets().add((Objects.requireNonNull(this.getClass().getResource("/profile-page.css"))).toExternalForm());
        if (ProfilePage.instance == null) {
            ProfilePage.instance = this;
        }

    }

    private static Parent root() throws FileNotFoundException {
        HBox hBox = new HBox();
        hBox.getChildren().addAll(menuBar(),mainPart());
        return hBox;
    }


    public void relocate() {
        if (stage != null) {
            stage.setX((bounds.getWidth() - getWidth()) / 2);
            stage.setY((bounds.getHeight() - getHeight()) / 2);
        }
    }




    public static AnchorPane menuBar() throws FileNotFoundException {
        AnchorPane anchorPane = new AnchorPane();

        Node logo = GuiUtil.logo();
        Node feedBtn = feedButton();
        Node profileBtn = profileButton();
        Node eventBtn = eventButton();
        Node logoutBtn = logOutButton();
        Node iMetButton = iMetButton();

        anchorPane.getChildren().addAll(logo, feedBtn, profileBtn, eventBtn,iMetButton, logoutBtn);

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
        return anchorPane;
    }
    private static Node logOutButton() {
        Button logOutButton = GuiUtil.createButton("L O G  O U T", "inactive-button-menu-bar");
        logOutButton.setOnAction(actionEvent ->{
            stage.setScene(LoginPage.instance);
            LoginPage.instance.relocate();
        });

        GuiUtil.buttonScaleTransitionMenu(logOutButton);
        return logOutButton;
    }


    private static Node profileButton() {
        Button profileButton = GuiUtil.createButton("P R O F I L E", "active-button-menu-bar");
        profileButton.setOnAction(actionEvent -> {
            stage.setScene(ProfilePage.instance);
            Feed.instance2.relocate();
        });
        GuiUtil.buttonScaleTransitionMenu(profileButton);

        return profileButton;
    }


    private static Node eventButton() {
        Button eventButton = GuiUtil.createButton("E V E N T", "inactive-button-menu-bar");
        eventButton.setOnAction(actionEvent -> {
            stage.setScene(Feed.instance2);
            Feed.instance2.relocate();
        });
        GuiUtil.buttonScaleTransitionMenu(eventButton);
        return eventButton;
    }

    private static Node feedButton() {
        Button feedButton = GuiUtil.createButton("F E E D", "inactive-button-menu-bar");
        feedButton.setOnAction(actionEvent -> {
            stage.setScene(Feed.instance2);
            Feed.instance2.relocate();
        });
        GuiUtil.buttonScaleTransitionMenu(feedButton);

        return feedButton;
    }
    private static Node iMetButton() {
        Button iMetButton = GuiUtil.createButton("I M E T", "inactive-button-menu-bar");
        iMetButton.setOnAction(actionEvent -> {
            try {
                Desktop.getDesktop().browse(new URI("https://imet.metropolitan.ac.rs/student/#/home"));
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        });
        GuiUtil.buttonScaleTransitionMenu(iMetButton);

        return iMetButton;
    }

    private static VBox mainPart() {
        VBox mainPart = new VBox();
        mainPart.getStyleClass().add("main-part");
        mainPart.setMinWidth(bounds.getWidth() * 0.8125);
        mainPart.setMaxWidth(bounds.getWidth() * 0.8125);
        return mainPart;
    }

    public static void setPrimaryStage(Stage stage) {
        ProfilePage.stage = stage;
    }

    /**
     * Returns the primary stage of the application.
     *
     * @return the primary stage of the application
     */
    public static Stage getPrimaryStage() {
        return stage;
    }
}


