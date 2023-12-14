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
        hBox.getChildren().addAll(menuBar(), mainPart(), secondPart());


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
        Button profileButton = GuiUtil.createButton("P R O F I L E", "inactive-button-menu-bar");
        profileButton.setOnAction(actionEvent -> {
            stage.setScene(ProfilePage.instance);
            ProfilePage.instance.relocate();
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
        Button feedButton = GuiUtil.createButton("F E E D", "active-button-menu-bar");
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
            ProfilePage.instance.relocate();

        });

        return hBox;
    }

}
