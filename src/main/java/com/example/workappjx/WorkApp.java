package com.example.workappjx;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.sql.DriverManager;

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

//        VBox vbox = new VBox(20);
//        Scene scene = new Scene(vbox, 400, 400);
//        primaryStage.setScene(scene);
//        DatePicker startDatePicker = new DatePicker();
//        DatePicker endDatePicker = new DatePicker();
//
//        startDatePicker.setValue(LocalDate.now());
//        endDatePicker.setValue(startDatePicker.getValue().plusDays(1));
//
//        vbox.getChildren().add(new Label("Start Date:"));
//        vbox.getChildren().add(startDatePicker);
//        vbox.getChildren().add(new Label("End Date:"));
//        vbox.getChildren().add(endDatePicker);
//        primaryStage.show();

    }




    public static void main(String[] args) {
        launch(args);
    }





}