package GUI;

import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class MessangerPage extends Scena{
    private static Stage stage;
    public static MessangerPage instance;
    private static final Rectangle2D bounds = screen.getVisualBounds();
    static {
        try {
            instance = new MessangerPage();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public MessangerPage() throws FileNotFoundException {
        super(root());
    }
    private static Parent root() throws FileNotFoundException {
        HBox hBox = new HBox();
        CustomMenuBar menuBar = new CustomMenuBar("inactive-button-menu-bar","inctive-button-menu-bar","active-button-menu-bar","inactive-button-menu-bar","inactive-button-menu-bar");
        AnchorPane anchorPane= menuBar.getCustomMenuBar();
        hBox.getChildren().addAll(anchorPane);
        return hBox;
    }

    public static void setPrimaryStage(Stage stage) {
        MessangerPage.stage = stage;
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
