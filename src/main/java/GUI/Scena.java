package GUI;

import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;


public abstract class Scena extends Scene {
    protected static Screen screen = Screen.getPrimary();
    private static final Rectangle2D bounds = screen.getVisualBounds();

    public Scena(Parent root) {
        super(root,
                bounds.getWidth(),
                bounds.getHeight());
    }
    public Scena(Parent parent, double v, double v1) {
        super(parent, v, v1);
    }

}


