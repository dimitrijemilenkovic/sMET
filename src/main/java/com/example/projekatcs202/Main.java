package com.example.projekatcs202;

import GUI.CustomMenuBar;
import GUI.GuiUtil;
import GUI.Feed;
import GUI.LoginPage;
import GUI.ProfilePage;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import networking.Client;


public class Main extends Application {

    //TODO:NAPRAVI SING UP PAGE I NAPRAVI LOGIN PAGE KAKO TREBA
    //TODO: DODAJ SLISKU SA IMETA NA SIGN IN  I SING UP PAGEU
    //KAO GOTOVO
    //TODO: CREATE PROFILE PAGE

    //TODO: CREATE EVENT PAGE



    @Override
    public void start(Stage stage) {
        setPrimStageForAll(stage);
        stage.setScene(LoginPage.instance);
        stage.setResizable(false);
        stage.show();
    }

    public void setPrimStageForAll(Stage stage){
        Feed.setPrimaryStage(stage);
        LoginPage.setPrimaryStage(stage);
        ProfilePage.setPrimaryStage(stage);
        CustomMenuBar.setPrimaryStage(stage);
        GuiUtil.setPrimaryStage(stage);

    }
    public static void main(String[] args) {
        launch();
    }
}