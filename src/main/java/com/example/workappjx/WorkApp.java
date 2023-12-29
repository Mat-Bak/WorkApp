package com.example.workappjx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Locale;

/*

Main file to start application. In the start() method is created new panel with scene.

 */


public class WorkApp extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("loginPanel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 250, 300);
        primaryStage.setResizable(false);
        primaryStage.setTitle("WorkApp");
        primaryStage.setScene(scene);
        primaryStage.show();

        Locale.setDefault(Locale.ENGLISH);

        //Connect to DB
        LoadPersonData personData = new LoadPersonData();
        personData.dbConnection();







    }




    public static void main(String[] args) {
        launch(args);
    }





}