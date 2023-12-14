package com.example.projekatcs202;

import GUI.Feed;
import GUI.LoginPage;
import GUI.ProfilePage;
import javafx.application.Application;
import javafx.stage.Stage;



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
//        MenuBar.setPrimaryStage(stage);

    }
    public static void main(String[] args) {
        launch();
    }
}