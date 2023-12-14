package Posts;

import GUI.GuiUtil;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.FileNotFoundException;
import java.util.Objects;

public class TextPost extends VBox {
    private String imeIPrezime;
    private String username;
    private String time;




    public TextPost(String text, String imeIPrezime, String username, String time) throws FileNotFoundException {
        this.getChildren().addAll(vBoxUpper(imeIPrezime, username), vBoxMiddle(text), vBoxLower(time));
        this.getStylesheets().add((Objects.requireNonNull(this.getClass().getResource("/feed-page.css"))).toExternalForm());
        this.setMaxWidth(600);
        this.setMinHeight(120);
        this.setMinWidth(600);

        this.getStyleClass().add("post");

//        this.setBackground(Background.fill(Paint.valueOf("#DAE3E5")));



    }

    private static VBox vBoxUpper(String imeIPrezime, String username) throws FileNotFoundException {
        VBox vBox = new VBox();
        HBox hBox = new HBox();
        VBox vBox1 = new VBox();
        vBox1.setAlignment(Pos.CENTER_LEFT);
        Label label = new Label(imeIPrezime);
        label.setId("label-white");
        label.getStyleClass().add("ime-i-prezime-label");



        Label label1 = new Label(username);
        label1.setId("label-white");
        label1.setOpacity(0.5);
        label1.setFont(Font.font("Areal", FontPosture.ITALIC, 15));
        label.setFont(Font.font("Areal", FontWeight.BOLD, 20));
        vBox1.getChildren().addAll(label, label1);
        hBox.getChildren().addAll(GuiUtil.createIcon("/Users/dimimac/INTELLIJ/JAVA II/PROJEKAT/projekat-cs202/assets/icons/avatar-48x48.png"),vBox1);
        vBox.getChildren().add(hBox);

        return vBox;
    }
    private static VBox vBoxMiddle(String text) {
        VBox vBox = new VBox();
        Label label = new Label(text);
        label.setId("label-white");
        label.setWrapText(true);
        label.setFont(Font.font("Areal",25));
        vBox.getChildren().add(label);
        return vBox;
    }
    private static VBox vBoxLower(String time) {
        VBox vBox = new VBox();
        Label label = new Label(time);
        vBox.setAlignment(Pos.BASELINE_RIGHT);
        label.setOpacity(0.5);
        label.setId("label-white");

        label.setFont(Font.font("Areal", FontPosture.ITALIC, 11));
        vBox.getChildren().add(label);
        return vBox;
    }





}
