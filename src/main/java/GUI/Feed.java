package GUI;

import Posts.TextPost;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


public class Feed extends Scena {
    private static Stage stage;
    private static final Rectangle2D bounds = screen.getVisualBounds();
    public static Feed instance2;
    private static final VBox mainPart = new VBox();

    private static final TextArea textArea = new TextArea();



    static {
        try {
            instance2 = new Feed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Feed() throws FileNotFoundException {
        super(root());
        this.getStylesheets().add((Objects.requireNonNull(this.getClass().getResource("/feed-page.css"))).toExternalForm());
        if (Feed.instance2 == null) {
            Feed.instance2 = this;
        }


    }

    private static Parent root() throws FileNotFoundException {
        HBox hBox = new HBox();
        hBox.getStyleClass().add("border-pane");
        CustomMenuBar menuBar = new CustomMenuBar("active-button-menu-bar","inactive-button-menu-bar","inactive-button-menu-bar","inactive-button-menu-bar");
        AnchorPane anchorPane= menuBar.getCustomMenuBar();
        hBox.getChildren().addAll(anchorPane, mainPart(), secondPart());


        return hBox;
    }



//

    private static ScrollPane mainPart() throws FileNotFoundException {

        mainPart.setMinWidth(bounds.getWidth() * 0.625);
        mainPart.setMaxWidth(bounds.getWidth() * 0.625);
        mainPart.setSpacing(20);
        mainPart.setAlignment(Pos.TOP_CENTER);
        ScrollPane scrollPane = new ScrollPane(mainPart);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mainPart.getStyleClass().add("main-part");
        mainPart.setMinHeight(1000);

        scrollPane.setMinWidth(bounds.getWidth() * 0.625);
        scrollPane.setMaxWidth(bounds.getWidth() * 0.625);

        AnchorPane anchorPane = postMakerArea();
        anchorPane.setMaxWidth(600);
        anchorPane.setMinWidth(600);

        anchorPane.setMinHeight(130);
        anchorPane.setMaxHeight(130);
        mainPart.getChildren().addAll(searchBar(),anchorPane,defaultPost());

        return scrollPane;
    }

    private static TextField searchBar() {
        TextField searchBar = new TextField();
        searchBar.setMaxWidth(600);
        searchBar.setMinWidth(600);
        searchBar.setMinHeight(50);
        searchBar.setMaxHeight(50);
        searchBar.setPromptText("Search sMet");
        return searchBar;
    }

    private static VBox secondPart() {
        VBox secondPart = new VBox();
        secondPart.getStyleClass().add("second-part");
        secondPart.setMinWidth(bounds.getWidth() * 0.1875);
        secondPart.setMaxWidth(bounds.getWidth() * 0.1875);

        secondPart.getChildren().add(profileBox("/Users/dimimac/INTELLIJ/JAVA II/PROJEKAT/projekat-cs202/assets/icons/avatar-48x48.png","Admin Team"));


        return secondPart;
    }



    private static AnchorPane postMakerArea() throws FileNotFoundException {
        AnchorPane anchorPane = new AnchorPane();

        Node sendButton = sendButton();
        Node textArea = textAreaMain();

        anchorPane.getChildren().addAll(textArea, sendButton);

        AnchorPane.setTopAnchor(textArea, 0d);
        AnchorPane.setLeftAnchor(textArea, 0d);

        AnchorPane.setTopAnchor(sendButton, 80d);
        AnchorPane.setRightAnchor(sendButton, 0d);


        anchorPane.getStyleClass().add("post-maker-area");
        return anchorPane;
    }

    private static Node sendButton () throws FileNotFoundException {
        Button sendButton = GuiUtil.createButtonTextIcon("POST    ", "send-button","/Users/dimimac/INTELLIJ/JAVA II/PROJEKAT/projekat-cs202/assets/icons/send.png");
        sendButton.getStyleClass().add("send-button");
        GuiUtil.buttonScaleTransition(sendButton);

        sendButton.setOnAction(actionEvent -> {
            post();
        });
        return sendButton;
    }

    private static Node textAreaMain() {

        textArea.setId("text-area-main");
        textArea.setPromptText("Write here...");

        textArea.setMinWidth(600);
        textArea.setMaxWidth(600);
        textArea.setMaxHeight(75);

        return textArea;
    }

    /**
     * Sets the primary stage for the application.
     *
     * @param stage the primary stage to be set
     */
    public static void setPrimaryStage(Stage stage) {
        Feed.stage = stage;
    }

    /**
     * Returns the primary stage of the application.
     *
     * @return the primary stage of the application
     */
    public static Stage getPrimaryStage() {
        return stage;
    }

    private static void post() {
        try {
            TextPost textPost = new TextPost(textArea.getText(),"Dimitrije Milenkovic", "@dmi", getCurrentDateTime());
            if (!textArea.getText().isEmpty()) {
                mainPart.getChildren().add(textPost);
                textArea.clear();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("You can't post an empty post.");
                alert.showAndWait();
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    private static String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

    private static TextPost defaultPost() throws FileNotFoundException {
        return new TextPost("Hello World!","Admin Team", "@smet", getCurrentDateTime());
    }

    public static HBox profileBox(String profilePictureAvatarPath, String username) {
        HBox hBox = new HBox();

        try {
            hBox.getChildren().add(GuiUtil.createIcon(profilePictureAvatarPath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        hBox.getStyleClass().add("profile-box");
        Label label = new Label(username);
        label.setId("label-white");
        label.setFont(Font.font("Areal", FontWeight.BOLD, 20));
        hBox.getChildren().add(label);
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(10, 10, 10, 10));

        GuiUtil.applyScaleTransition(hBox,0.95,0.95,200,300);
        hBox.setOnMouseClicked(mouseEvent -> {
            stage.setScene(ProfilePage.instance);
            GuiUtil.relocate(ProfilePage.instance);

        });

        return hBox;
    }

}
