package com.example.workappjx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;



public class MainPanel{
    @FXML
    public Pane mainPanel;
    @FXML
    public Pane personInfoPanel;
    @FXML
    public TextArea personInfo;

    @FXML
    public Text firstNameLabel;
    @FXML
    public Text lastNameLabel;
    @FXML
    public Text phoneNumberLabel;
    @FXML
    public Text peselLabel;
    @FXML
    public Pane workPanel;

    @FXML
    public Button addWorkTimeButton;

    @FXML
    public DatePicker dateTime;
    public Person person;

    public LocalDate getLocalDate;

    public Person getPerson() {
        return person;
    }

    public void returnData(){
        System.out.println("Data: " + getLocalDate);
//        return getLocalDate;
    }

    public void mainPanel() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("mainPanel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 450, 400);
        Stage stage = new Stage();
        stage.setTitle("WorkApp");
        stage.setScene(scene);
        stage.show();


    }

    // Show panel with person info
    public void showPersonInfo() throws IOException {
        mainPanel.setVisible(false);
        personInfoPanel.setVisible(true);
        setPersonData();

    }

    // back to main panel from person info panel
    public void personPanelBack(){
        mainPanel.setVisible(true);
        personInfoPanel.setVisible(false);
    }

    //show work panel
    public void showWorkPanel() throws IOException {
        mainPanel.setVisible(false);
        workPanel.setVisible(true);
    }

    // back to main panel from work panel
    public void workPanelBack(){
        mainPanel.setVisible(true);
        workPanel.setVisible(false);
    }

    // set data in person info panel
    public void setPersonData() throws IOException {
        Person person = LoginPanelController.getPersonData();

         firstNameLabel.setText("First Name: " + person.getFirstName());
         lastNameLabel.setText("Last Name: " + person.getLastName());
         phoneNumberLabel.setText("Phone Number: " + person.getPhoneNumber());
         peselLabel.setText("Pesel: " + person.getPesel());

    }

    public void getDataTime(){

//        getLocalDate = dateTime.getValue();
        getLocalDate = dateTime.getValue();
        System.out.println("MainPanel Data: " + getLocalDate);
//        if(localDate != null){
//            System.out.println("Data: " + localDate);
//            System.out.println("Day: " + localDate.getDayOfMonth());
//            System.out.println("Month: " + localDate.getMonth().toString());
//            System.out.println("Year: " +localDate.getYear());
//        }

    }

    public void createWorkPanel() throws IOException {
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader();
//            fxmlLoader.setLocation(getClass().getResource("addWorkTime.fxml"));
//            /*
//             * if "fx:controller" is not set in fxml
//             * fxmlLoader.setController(NewWindowController);
//             */
//            Stage stage = (Stage) addWorkTimeButton.getScene().getWindow();
//            Scene scene = new Scene(fxmlLoader.load(), 300, 400);
//            stage.setTitle("New Window");
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("addWorkTime.fxml"));
//        Scene secondScene = new Scene(fxmlLoader.load(), 300, 400);
//        Stage Secondstage = new Stage();
//        Secondstage.setTitle("Add Work Time");
//        Secondstage.setScene(secondScene);
//        Secondstage.show();

        AddWorkTime workTimePanel = new AddWorkTime();
        workTimePanel.testLocalDate = dateTime.getValue();
        System.out.println("workTimePanel: " + workTimePanel.testLocalDate);

        workTimePanel.addWorkTimeWindow();
    }
}
