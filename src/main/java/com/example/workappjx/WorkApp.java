package com.example.workappjx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/*

Main file to start application. In the start() method is created new panel with scene.

 */


public class WorkApp extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("loginPanel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 250, 300);
//        scene.getStylesheets().add(getClass().getResource("com/example/workappjx/style.css").toExternalForm());
//        scene.getStylesheets().add("com/example/workappjx/style.css");
//        scene.getStylesheets().add(getClass().getResource("src/style.css").toString());
        primaryStage.setTitle("WorkApp");
        primaryStage.setScene(scene);
        primaryStage.show();

        //Connect to DB
        LoadPersonData personData = new LoadPersonData();
        personData.dbConnection();





    }




    public static void main(String[] args) {
        launch(args);
    }





}