package GUI;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileInputStream;
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
        CustomMenuBar menuBar = new CustomMenuBar("inactive-button-menu-bar","active-button-menu-bar","inactive-button-menu-bar","inactive-button-menu-bar","inactive-button-menu-bar");
        AnchorPane anchorPane= menuBar.getCustomMenuBar();
        hBox.getChildren().addAll(anchorPane,mainPart());
        return hBox;
    }






    private static AnchorPane mainPart() throws FileNotFoundException {
        AnchorPane mainPart = new AnchorPane();
        mainPart.getStyleClass().add("main-part");

        Node profilePicturePanel = profilePicturePanel();

        mainPart.getChildren().add(profilePicturePanel);

        mainPart.setMinWidth(bounds.getWidth() * 0.8125);
        mainPart.setMaxWidth(bounds.getWidth() * 0.8125);

        AnchorPane.setTopAnchor(profilePicturePanel, 10d);
        AnchorPane.setLeftAnchor(profilePicturePanel, 10d);


        return mainPart;
    }

    private static VBox profilePicturePanel() throws FileNotFoundException {
        VBox profilePicturePanel = new VBox();
        profilePicturePanel.getStyleClass().add("profile-picture-panel");
        Image profilePicture = new Image( new FileInputStream("/Users/dimimac/INTELLIJ/JAVA II/PROJEKAT/projekat-cs202/assets/photos/pptemp1.png"));

        ImageView imageView = new ImageView(profilePicture);
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);

        Label label = new Label("Admin Team");

        HBox hBox = new HBox();
        Button button = new Button("Follow");
        Button button1 = new Button("Message");

        profilePicturePanel.setBackground(Background.fill(Paint.valueOf("#FFFFFF")));

        hBox.getChildren().addAll(button,button1);
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        profilePicturePanel.getChildren().addAll(imageView,label,hBox);

        profilePicturePanel.setSpacing(10);
        profilePicturePanel.setAlignment(Pos.CENTER);



        return profilePicturePanel;
    }

    private static VBox socialNetworkPanel() {
        VBox socialNetworkPanel = new VBox();
        socialNetworkPanel.getStyleClass().add("social-network-panel");
        return socialNetworkPanel;


    }

    private static HBox socialNetworkBar(ImageView socialNetworkIcon,String socialNetworkName,String socialNetworkLink) {
        HBox socialNetworkBar = new HBox();
        socialNetworkBar.getStyleClass().add("social-network-bar");
        Label soccialNetworkName = new Label (socialNetworkName);
        socialNetworkBar.getChildren().addAll(socialNetworkIcon,soccialNetworkName);
        socialNetworkBar.setSpacing(10);
        socialNetworkBar.setAlignment(Pos.CENTER_LEFT);
        socialNetworkBar.setOnMouseClicked(event -> {

            if (socialNetworkLink == null) {

            }
            try {
                Desktop.getDesktop().browse(new URI(socialNetworkLink));
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        });

        return socialNetworkBar;
    }

    private static TilePane socialNetworkNameInput(String socialNetworkName) {
        TilePane socialNetworkNameInput = new TilePane();
        socialNetworkNameInput.getStyleClass().add("social-network-name-input");
        TextInputDialog td = new TextInputDialog("Enter URL here");
        td.setHeaderText("Enter URL to your + " + socialNetworkName);
        td.showAndWait();
        return socialNetworkNameInput;

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


