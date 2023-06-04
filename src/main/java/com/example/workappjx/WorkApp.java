package com.example.workappjx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class WorkApp extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("loginPanel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 250, 300);
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