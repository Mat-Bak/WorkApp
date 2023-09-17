package com.example.workappjx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/*

Methods:
    * void mainPanel() - create main menu panel with general options
    * void showPersonInfo() - set person info panel visible and hide main menu panel
    * void personPanelBack() - hide person info panel and show main menu panel
    * void showWorkPanel() - show panel with calendar and work data, hide main menu panel
    * void workPanelBack() - hide work data panel and show main menu panel
    * void setPersonData() - set user data to info panel
    * void getDataTime() - get selected data time from calendar
    * void showWorkTimeData() - show every work data for selected data time and selected user
    * void createWorkPanel() - create new work panel where user can add new work time data

The class manages main menu where user can switch between: main panel, user info panel, work time panel (in the future will be more panels).

 */


public class MainPanel implements Initializable {
    @FXML
    public Pane mainPanel;

    @FXML
    public Pane salaryPanel;
    @FXML
    public Pane personInfoPanel;

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

    public static LocalDate getLocalDate;

    @FXML
    public VBox workTimeDataPanel;

    @FXML
    public ComboBox monthList;

    @FXML
    public Text SalaryHours;
    @FXML
    public Text SalaryBrutto;
    @FXML
    public Text SalaryNetto;

    private String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};



    public Person getPerson() {
        return person;
    }


    public void mainPanel() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("mainPanel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 450, 400);
        Stage stage = new Stage();
        //E:/Nauka/Java/workApp/workAppJX/src/main/resources/
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
    
    public void showSalaryPanel(){
        mainPanel.setVisible(false);
        salaryPanel.setVisible(true);
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

        getLocalDate = dateTime.getValue();
        System.out.println("MainPanel Data: " + getLocalDate);
    }

    public HBox CreateHBox(String address, String time){
        HBox hbox = new HBox();
        Text workAddress = new Text(address);
        Text workTime = new Text(time);
        workAddress.setTextAlignment(TextAlignment.CENTER);
        workTime.setTextAlignment(TextAlignment.RIGHT);
//
//        HBox.setHgrow(workAddress, Priority.ALWAYS);
//        HBox.setHgrow(workTime, Priority.ALWAYS);

        hbox.setMargin(workAddress, new Insets(20, 20, 20, 20));
        hbox.setMargin(workTime, new Insets(20, 20, 20, 20));
        hbox.getChildren().addAll(workAddress, workTime);

        return hbox;

    }

    public void showWorkTimeData(){
        Person person = LoginPanelController.getPersonData();
        LocalDate localDate = dateTime.getValue();
        if(localDate == null) return;
        System.out.println("Person ID: " + person.getId());
        LoadWorkTimeData loadWorkTimeData = new LoadWorkTimeData();
        List<WorkTime> workTimeList = loadWorkTimeData.dbConnection(person.getId());
        System.out.println("Dlugość tablicy workTime: " + workTimeList.size());
        workTimeDataPanel.getChildren().clear();
        for(WorkTime workTime : workTimeList){
            if (localDate.isEqual(workTime.getDate())){
                Text address = new Text("Address: \n" + workTime.getAddress());
                Text time = new Text("Time: \n " + workTime.getHoursWork());
                BorderPane  hbox = new BorderPane ();
                hbox.setPrefWidth(Double.MAX_VALUE);
                hbox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                        + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                        + "-fx-border-radius: 5;" + "-fx-border-color: blue;");

                address.setTextAlignment(TextAlignment.CENTER);
                time.setTextAlignment(TextAlignment.RIGHT);
                address.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                        + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                        + "-fx-border-radius: 5;" + "-fx-border-color: red;");
                time.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                        + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                        + "-fx-border-radius: 5;" + "-fx-border-color: green;");

                hbox.setMargin(address, new Insets(0, 20, 0, 20));
                hbox.setMargin(time, new Insets(0, 20, 0, 20));
                hbox.setCenter(address);
                hbox.setRight(time);
//                hbox.getChildren().addAll(address, time);
                HBox.setHgrow(address, Priority.ALWAYS);
                HBox.setHgrow(time, Priority.ALWAYS);
                workTimeDataPanel.getChildren().add(hbox);

            }
        }
    }
/*
    public void showWorkTimeData(){
        Person person = LoginPanelController.getPersonData();
        LocalDate localDate = dateTime.getValue();
        if(localDate == null) return;
        System.out.println("Person ID: " + person.getId());
        LoadWorkTimeData loadWorkTimeData = new LoadWorkTimeData();
        List<WorkTime> workTimeList = loadWorkTimeData.dbConnection(person.getId());
        System.out.println("Dlugość tablicy workTime: " + workTimeList.size());
        workTimeDataPanel.getChildren().clear();
        for (WorkTime workTime : workTimeList) {
            if(localDate.isEqual(workTime.getDate())){
                SplitPane pane = new SplitPane();

                AnchorPane firstArchonPane = new AnchorPane();
                AnchorPane secondArchonPane = new AnchorPane();
                firstArchonPane.setPadding(new Insets(20));
                secondArchonPane.setPadding(new Insets(20));

                Text addressText = new Text("Address: ");
                addressText.setLayoutY(27);

                Text exampelAddress = new Text("Example Address");
                exampelAddress.setLayoutY(49);

                firstArchonPane.getChildren().add(addressText);
                firstArchonPane.getChildren().add(exampelAddress);

                Text hoursText = new Text("Hours: ");
                hoursText.setLayoutY(27);

                Text hoursTime = new Text("Example Address");
                hoursTime.setLayoutY(49);

                secondArchonPane.getChildren().add(hoursText);
                secondArchonPane.getChildren().add(hoursTime);

                AnchorPane.setLeftAnchor(firstArchonPane, 0.0);

                pane.getItems().addAll(firstArchonPane,secondArchonPane);

                BorderStroke borderStroke = new BorderStroke(
                        Color.GREY, // Kolor obramowania
                        BorderStrokeStyle.SOLID, // Styl obramowania
                        CornerRadii.EMPTY, // Promienie rogów (w tym przypadku brak promieni)
                        BorderWidths.DEFAULT // Szerokości obramowania
                );
                Border border = new Border(borderStroke);
                pane.setBorder(border);
                workTimeDataPanel.getChildren().add(pane);
            }

        }
    }
    */

    public void createWorkPanel() throws IOException {

        AddWorkTime workTimePanel = new AddWorkTime();
        getLocalDate = dateTime.getValue();
//        workTimePanel.setDateText(getLocalDate.toString());
//        System.out.println("get Date: " + getLocalDate.toString() );
//        workTimePanel.setDateText("test");
        workTimePanel.addWorkTimeWindow(dateTime.getValue());
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1){

        for(int i = 0; i < months.length; ++i){
            monthList.getItems().add(months[i]);
        }
    }

    public void summaryOfTheMonth(){
        int month = 0;
        Person person = LoginPanelController.getPersonData();
        System.out.println("Person id: " + person.getId());
        LoadWorkTimeData loadWorkTimeData = new LoadWorkTimeData();
        List<WorkTime> workTimeList = loadWorkTimeData.dbConnection(person.getId());
        System.out.println("id3: " + monthList.getValue());
        for (int i = 0; i < months.length; ++i){
            if(months[i].equals(monthList.getValue())){
                ++i;
                month=i;
                System.out.println("Month: " + i);
                break;
            }
        }
        long hours = workTimeList.get(0).getHoursWork().toHours();
        long min = workTimeList.get(0).getHoursWork().toMinutes() - (hours*60);
        long SumOfHours = 0;
        for(int j = 0; j < workTimeList.size(); ++j){
            if(workTimeList.get(j).getDate().getMonthValue() == month){
                LocalTime time1 = workTimeList.get(j).getStart_time();  // Pierwsza godzina
                LocalTime time2 = workTimeList.get(j).getEnd_time();  // Druga godzina
                Duration duration = Duration.between(time1, time2);
//                long hours = duration.toHours();  // Różnica w godzinach
//                long minutes = duration.toMinutes() % 60;  // Różnica w minutach
                SumOfHours += duration.toMinutes();  // Różnica w minutach
            }
        }
        SalaryHours.setText("");
        SalaryHours.setText("Hours: " + SumOfHours/60 + "h " + SumOfHours%60 + "min");
        SalaryBrutto.setText("");
        SalaryBrutto.setText("Brutto: " + (SumOfHours/60)*person.getSalaryPerHour());
        SalaryNetto.setText("");
        float tax = 1 - (float)person.getTax()/100;
        System.out.println("PersonTax: " + person.getTax() + "\n tax: " + tax);
        SalaryNetto.setText("Netto: " +  (SumOfHours/60)*person.getSalaryPerHour()*tax);
        System.out.println("WorkTime data getMonthValue: " + workTimeList.get(0).getDate().getMonthValue());
//        System.out.println("WorkTime work hours: " + hours + "h " + min + "min");
        System.out.println("WorkTime start time: " + workTimeList.get(0).getStart_time() + " | end time: "+ workTimeList.get(0).getEnd_time());
    }

//    public void clearDataPicker(){
//        LocalDate date = LocalDate.of(0001, 01, 01);
//        dateTime.setValue(date);
//    }

}


