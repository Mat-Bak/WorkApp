package com.example.workappjx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
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


public class MainPanel implements Initializable{
    @FXML
    public Pane mainPanel;

    @FXML
    public Pane salaryPanel;
    @FXML
    public Pane personInfoPanel;

    @FXML
    public Pane settingsPanel;

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

//    public LocalDate localDate;

    public static LocalDate getLocalDate;

    @FXML
    public VBox workTimeDataPanel;

    @FXML
    public ComboBox monthList;

    @FXML
    public Text testAdmin;

    @FXML
    public Text SalaryHours;
    @FXML
    public Text SalaryBrutto;

    @FXML
    public Text salaryLabel;

    @FXML
    public PasswordField oldPass;
    @FXML
    public PasswordField newPass;

//    @FXML
//    public ScrollPane scrollTest;
//
//    @FXML
//    public Button refreshData;

    private String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};


    public int startHour;
    public int startMinuts;
    public int endHour;
    public int endMinuts;

    public int id;

    public static int workTimeID;


//    public int startHourReturn(){
//        System.out.println("StartHour: " + startHour);
//        return startHour;
//    }
//    public int startMinutsReturn(){
//        System.out.println("StartMin: " + startMinuts);
//        return startMinuts;
//    }
//    public int endHourReturn(){
//        System.out.println("endHour: " + endHour);
//        return endHour;
//    }
//    public int endMinutsReturn(){
//        System.out.println("endMin: " + endHour);
//        return endMinuts;
//    }


//    public Person getPerson() {
//        return person;
//    }


    public void mainPanel() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("mainPanel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 450, 400);
        Stage stage = new Stage();
        //E:/Nauka/Java/workApp/workAppJX/src/main/resources/
        stage.setTitle("WorkApp");
        stage.setScene(scene);
        stage.show();
        person = LoginPanelController.getPersonData();
    }

//    public int returnID(){
//        return id;
//    }

//    public void fireButton(WindowEvent event){
//        refreshData.fire();
//        System.out.println("BUTTON FIRED!");
//    }

    public void showAdminTest(){
        testAdmin.setVisible(true);
    }

    public void hideAdminTest(){
        testAdmin.setVisible(false);
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
        workTimeDataPanel.getChildren().clear();
        dateTime.setValue(null);
        mainPanel.setVisible(false);
        workPanel.setVisible(true);
    }

    // back to main panel from work panel
    public void workPanelBack(){
        mainPanel.setVisible(true);
        workPanel.setVisible(false);
    }
    
    public void showSalaryPanel(){

        monthList.setValue(null);
        SalaryHours.setText("Hours: ");
        SalaryBrutto.setText("Brutto: ");
//        SalaryNetto.setText("Netto: ");
//        taxText.setText("Tax: ");
        mainPanel.setVisible(false);
        salaryPanel.setVisible(true);
    }

    public void salaryPanelBack(){
        mainPanel.setVisible(true);
        salaryPanel.setVisible(false);
    }

    public void showSettingsPanel(){
        oldPass.clear();
        newPass.clear();
        mainPanel.setVisible(false);
        settingsPanel.setVisible(true);
    }
    public void settingPanelBack(){
        mainPanel.setVisible(true);
        settingsPanel.setVisible(false);
    }

    // set data in person info panel
    public void setPersonData() throws IOException {
        Person person = LoginPanelController.getPersonData();

         firstNameLabel.setText("First Name: " + person.getFirstName());
         lastNameLabel.setText("Last Name: " + person.getLastName());
         phoneNumberLabel.setText("Phone Number: " + person.getPhoneNumber());
         peselLabel.setText("Pesel: " + person.getPesel());
        salaryLabel.setText("Salary per hour: " + person.getSalaryPerHour());


    }

//    public void testCloseRequest() throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("addWorkTime.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 300, 460);
//        Stage stage = new Stage();
//        stage.setTitle("Add Work Time");
//        stage.setScene(scene);
//        stage.setOnCloseRequest(this::fireButton);
//    }


    public LocalDate getDataTime(){

        getLocalDate = dateTime.getValue();
        return getLocalDate;
//        System.out.println("MainPanel Data: " + getLocalDate);
    }


//    public void setDataTime(LocalDate timeDate){
//        dateTime.setValue(timeDate);
//    }

//    public HBox CreateHBox(String address, String time){
//        HBox hbox = new HBox();
//        Text workAddress = new Text(address);
//        Text workTime = new Text(time);
//        workAddress.setTextAlignment(TextAlignment.CENTER);
//        workTime.setTextAlignment(TextAlignment.RIGHT);
////
////        HBox.setHgrow(workAddress, Priority.ALWAYS);
////        HBox.setHgrow(workTime, Priority.ALWAYS);
//
//        hbox.setMargin(workAddress, new Insets(20, 20, 20, 20));
//        hbox.setMargin(workTime, new Insets(20, 20, 20, 20));
//        hbox.getChildren().addAll(workAddress, workTime);
//
//        return hbox;
//
//    }

    public void showWorkTimeData(){
        System.out.println("Test czy działą button!");
        Person person = LoginPanelController.getPersonData();
        LocalDate localDate = getDataTime();
//        localDate = dateTime.getValue();
//        System.out.println("LocalDate: " + localDate);
        if(localDate == null) return;
//        System.out.println("Person ID: " + person.getId());
        LoadWorkTimeData loadWorkTimeData = new LoadWorkTimeData();
        List<WorkTime> workTimeList = loadWorkTimeData.dbConnection(person.getId(), localDate);
//        System.out.println("Dlugość tablicy workTime: " + workTimeList.size());
        workTimeDataPanel.getChildren().clear();


        for(WorkTime workTime : workTimeList){
            Text address = new Text(workTime.getAddress());
            Text time = new Text(workTime.timeToStrikg());
            address.setTextAlignment(TextAlignment.CENTER);
            time.setTextAlignment(TextAlignment.CENTER);

            TextFlow dataPane = new TextFlow();
            TextFlow timePane = new TextFlow();
//            TextFlow buttonsEditPane = new TextFlow();
//            TextFlow buttonsDeletePane = new TextFlow();

//            Button edit = new Button("E");
//            Button delete = new Button("D");
//            edit.setPrefSize(25,25);
//            delete.setPrefSize(25,25);

//            buttonsEditPane.setTextAlignment(TextAlignment.CENTER);
//            buttonsEditPane.setPrefSize(35, 39);
//            buttonsEditPane.getChildren().add(edit);
//            buttonsEditPane.setLayoutX(0);
//            buttonsDeletePane.setLayoutX(35);
//            buttonsEditPane.setPadding(new Insets(5,5,5,5));
//
//            buttonsDeletePane.setTextAlignment(TextAlignment.CENTER);
//            buttonsDeletePane.setPrefSize(35, 39);
//            buttonsDeletePane.getChildren().add(delete);
//            buttonsDeletePane.setPadding(new Insets(5,5,5,5));

            dataPane.setTextAlignment(TextAlignment.CENTER);
            dataPane.setPrefSize(285, 39);
            dataPane.getChildren().add(address);
            dataPane.setLayoutX(0);
            dataPane.setPadding(new Insets(5,5,5,5));

            timePane.setTextAlignment(TextAlignment.CENTER);
            timePane.setPrefSize(94, 39);
            timePane.getChildren().add(time);
            timePane.setLayoutX(285);
            timePane.setPadding(new Insets(5,5,5,5));

            Pane pane = new Pane();
            pane.setPrefWidth(380);
            pane.setPrefHeight(40);
            pane.getChildren().addAll(dataPane, timePane);
//            pane.getChildren().addAll(buttonsEditPane, buttonsDeletePane,dataPane, timePane);

            pane.setStyle("-fx-border-color: grey;" +
                    "-fx-border-style: solid none solid none;" +
                    "-fx-border-width: 2;" +
                    "-fx-background-color: lightgrey;");

            dataPane.setStyle("-fx-border-color: grey;" +
                    "-fx-border-style: hidden solid hidden hidden;" +
                    "-fx-border-width: 4;");
//            edit.setOnMouseEntered(event ->{
//                edit.setCursor(Cursor.HAND);
//            });
//            delete.setOnMouseEntered(event ->{
//                delete.setCursor(Cursor.HAND);
//            });

            pane.setOnMouseEntered(event ->{
                pane.setStyle("-fx-border-color: lightgrey;" +
                        "-fx-border-style: solid none solid none;" +
                        "-fx-border-width: 2;" +
                        "-fx-background-color: grey;");
                pane.setCursor(Cursor.HAND);
            });

            pane.setOnMouseExited(event ->{
                pane.setStyle("-fx-border-color: grey;" +
                        "-fx-border-style: solid none solid none;" +
                        "-fx-border-width: 2;" +
                        "-fx-background-color: lightgrey;");
                pane.setCursor(Cursor.DEFAULT);
            });

            pane.setOnMouseClicked(event -> {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("editWorkTime.fxml"));
                Scene secondScene = null;
                try {
                    secondScene = new Scene(fxmlLoader.load(), 300, 460);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Stage Secondstage = new Stage();
                Secondstage.setScene(secondScene);
                Secondstage.show();


                startHour = workTime.getStart_time().getHour();
                startMinuts = workTime.getStart_time().getMinute();
                endHour = workTime.getEnd_time().getHour();
                endMinuts = workTime.getEnd_time().getMinute();
                id = workTime.getUser_id();

                EditWorkTime editWorkTime = fxmlLoader.getController();
                editWorkTime.setData(startHour, startMinuts, endHour, endMinuts, workTime.getAddress(), workTime.getComment());
//                System.out.println("ID worktime: " + workTime.getId());
                workTimeID = workTime.getId();


            });

//            edit.setOnMouseClicked(event -> {
//                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setLocation(getClass().getResource("editWorkTime.fxml"));
//                Scene secondScene = null;
//                try {
//                    secondScene = new Scene(fxmlLoader.load(), 300, 460);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                Stage Secondstage = new Stage();
//                Secondstage.setScene(secondScene);
//                Secondstage.show();
//
//
//                startHour = workTime.getStart_time().getHour();
//                startMinuts = workTime.getStart_time().getMinute();
//                endHour = workTime.getEnd_time().getHour();
//                endMinuts = workTime.getEnd_time().getMinute();
//                id = workTime.getUser_id();
//
//                EditWorkTime editWorkTime = fxmlLoader.getController();
//                editWorkTime.setData(startHour, startMinuts, endHour, endMinuts, workTime.getAddress(), workTime.getComment());
////                System.out.println("ID worktime: " + workTime.getId());
//                workTimeID = workTime.getId();
//
//
//            });
//            delete.setOnMouseClicked(event -> {
//                Stage popupWindow = new Stage();
//                popupWindow.initModality(Modality.APPLICATION_MODAL);
//                popupWindow.setTitle("Delete");
//
//                StackPane popupRoot = new StackPane();
//                VBox popupVBox = new VBox(10);
//                popupVBox.setPadding(new Insets(10));
//
//                Label popupLabel = new Label("Do you want delete?");
//                Button buttonYes = new Button("YES");
//                Button buttonNo = new Button("NO");
//                HBox buttonsHBox = new HBox(10);
//                popupVBox.getChildren().add(popupLabel);
//                popupVBox.setAlignment(Pos.CENTER);
//                buttonsHBox.getChildren().addAll(buttonYes, buttonNo);
//                buttonsHBox.setAlignment(Pos.CENTER);
//
//                VBox popupPane = new VBox(10);
//                popupPane.getChildren().addAll(popupVBox, buttonsHBox);
//                popupPane.setAlignment(Pos.CENTER);
//
//                Scene popupScene = new Scene(popupPane, 300, 200);
//                popupWindow.setScene(popupScene);
//                popupWindow.show();
//
//                buttonNo.setOnMouseClicked(e ->{
//                    popupWindow.close();
//                });
//                workTimeID = workTime.getId();
//                buttonYes.setOnMouseClicked(ev ->{
//                    SaveWorkTimeData saveWorkTimeData = new SaveWorkTimeData();
//                    System.out.println("Delete record where id = " + workTimeID);
//                    saveWorkTimeData.removeDataFromDataBase(workTimeID);
//                    popupWindow.close();
//                });
//
//            });
            workTimeDataPanel.getChildren().add(pane);

        }
    }

//    public int returnWorkTitemID(){
//        return workTimeID;
//    }

//    public void addNewPanel(WorkTime workTime){
//        Text address = new Text(workTime.getAddress());
//        Text time = new Text(workTime.timeToStrikg());
//        address.setTextAlignment(TextAlignment.CENTER);
//        time.setTextAlignment(TextAlignment.CENTER);
//
//        TextFlow dataPane = new TextFlow();
//        TextFlow timePane = new TextFlow();
//        TextFlow buttonsEditPane = new TextFlow();
//        TextFlow buttonsDeletePane = new TextFlow();
//
//        Button edit = new Button("E");
//        Button delete = new Button("D");
//        edit.setPrefSize(25,25);
//        delete.setPrefSize(25,25);
//
//        buttonsEditPane.setTextAlignment(TextAlignment.CENTER);
//        buttonsEditPane.setPrefSize(35, 39);
//        buttonsEditPane.getChildren().add(edit);
//        buttonsEditPane.setLayoutX(0);
//        buttonsDeletePane.setLayoutX(35);
//        buttonsEditPane.setPadding(new Insets(5,5,5,5));
//
//        buttonsDeletePane.setTextAlignment(TextAlignment.CENTER);
//        buttonsDeletePane.setPrefSize(35, 39);
//        buttonsDeletePane.getChildren().add(delete);
//        buttonsDeletePane.setPadding(new Insets(5,5,5,5));
//
//        dataPane.setTextAlignment(TextAlignment.CENTER);
//        dataPane.setPrefSize(215, 39);
//        dataPane.getChildren().add(address);
//        dataPane.setLayoutX(70);
//        dataPane.setPadding(new Insets(5,5,5,5));
//
//        timePane.setTextAlignment(TextAlignment.CENTER);
//        timePane.setPrefSize(94, 39);
//        timePane.getChildren().add(time);
//        timePane.setLayoutX(285);
//        timePane.setPadding(new Insets(5,5,5,5));
//
//        Pane pane = new Pane();
//        pane.setPrefWidth(380);
//        pane.setPrefHeight(40);
//        pane.getChildren().addAll(buttonsEditPane, buttonsDeletePane,dataPane, timePane);
//
//        pane.setStyle("-fx-border-color: grey;" +
//                "-fx-border-style: solid none solid none;" +
//                "-fx-border-width: 2;" +
//                "-fx-background-color: lightgrey;");
//
//        dataPane.setStyle("-fx-border-color: grey;" +
//                "-fx-border-style: hidden solid hidden solid;" +
//                "-fx-border-width: 4;");
//        edit.setOnMouseEntered(event ->{
//            edit.setCursor(Cursor.HAND);
//        });
//        delete.setOnMouseEntered(event ->{
//            delete.setCursor(Cursor.HAND);
//        });
//
//        pane.setOnMouseEntered(event ->{
//            pane.setStyle("-fx-border-color: lightgrey;" +
//                    "-fx-border-style: solid none solid none;" +
//                    "-fx-border-width: 2;" +
//                    "-fx-background-color: grey;");
////                pane.setCursor(Cursor.HAND);
//        });
//
//        pane.setOnMouseExited(event ->{
//            pane.setStyle("-fx-border-color: grey;" +
//                    "-fx-border-style: solid none solid none;" +
//                    "-fx-border-width: 2;" +
//                    "-fx-background-color: lightgrey;");
//            pane.setCursor(Cursor.DEFAULT);
//        });
//        workTimeDataPanel.getChildren().add(pane);
//    }
//    public void getDataOnClick(ActionEvent event){
//
//    }

//    public Button returnButtonFire(){
//        return refreshData;
//    }

//    public void testButtonFire(){
//        LocalDate date = getDataTime();
//        if(date == null)return;
//        Person person = LoginPanelController.getPersonData();
//        LoadWorkTimeData loadWorkTimeData = new LoadWorkTimeData();
//        List<WorkTime> workTimeList = loadWorkTimeData.dbConnection(person.getId(), date);
//        workTimeDataPanel.getChildren().clear();
//        System.out.println("Window Clear!");
//        for(WorkTime workTime : workTimeList) {
//            Text address = new Text(workTime.getAddress());
//            Text time = new Text(workTime.timeToStrikg());
//            address.setTextAlignment(TextAlignment.CENTER);
//            time.setTextAlignment(TextAlignment.CENTER);
//
//            TextFlow dataPane = new TextFlow();
//            TextFlow timePane = new TextFlow();
//
//            dataPane.setTextAlignment(TextAlignment.CENTER);
//            dataPane.setPrefSize(285, 39);
//
//            timePane.setTextAlignment(TextAlignment.CENTER);
//            timePane.setPrefSize(95, 39);
//
//            dataPane.getChildren().add(address);
//            dataPane.setLayoutX(0);
//
//            timePane.getChildren().add(time);
//            timePane.setLayoutX(285);
//
//            Pane pane = new Pane();
//            pane.setPrefWidth(380);
//            pane.setPrefHeight(40);
//            pane.getChildren().addAll(dataPane, timePane);
//
//            pane.setStyle("-fx-border-color: grey;" +
//                    "-fx-border-style: solid none solid none;" +
//                    "-fx-border-width: 2;");
//
//            dataPane.setStyle("-fx-border-color: grey;" +
//                    "-fx-border-style: hidden solid hidden hidden;" +
//                    "-fx-border-width: 4;");
//
//            workTimeDataPanel.getChildren().add(pane);
//        }
//        }





    public void createWorkPanel() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("addWorkTime.fxml"));
        Scene secondScene = new Scene(fxmlLoader.load(), 300, 460);
        Stage Secondstage = new Stage();
        Secondstage.setTitle("Add Work Time");
        Secondstage.setScene(secondScene);
        Secondstage.show();

//        AddWorkTime workTimePanel = new AddWorkTime();
//        getLocalDate = dateTime.getValue();
////        workTimePanel.setDateText(getLocalDate.toString());
////        System.out.println("get Date: " + getLocalDate.toString() );
////        workTimePanel.setDateText("test");
//        workTimePanel.addWorkTimeWindow(dateTime.getValue());
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        Person person = LoginPanelController.getPersonData();
        System.out.println("getAdmin: " + person.getAdmin());
        if(person.getAdmin() == true){
            showAdminTest();
            System.out.println("Admin Test Visible!");
        }else{
            hideAdminTest();
            System.out.println("Admin Test Hidden!");
        }

        for(int i = 0; i < months.length; ++i){
            monthList.getItems().add(months[i]);
        }
    }

    public void summaryOfTheMonth(){
        int month = 0;
        Person person = LoginPanelController.getPersonData();
//        System.out.println("Person id: " + person.getId());
        LoadWorkTimeData loadWorkTimeData = new LoadWorkTimeData();
        List<WorkTime> workTimeList = loadWorkTimeData.getDataFromWorkTime(person.getId());
//        System.out.println("id3: " + monthList.getValue());
        for (int i = 0; i < months.length; ++i){
            if(months[i].equals(monthList.getValue())){
                ++i;
                month=i;
//                System.out.println("Month: " + i);
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
        SalaryBrutto.setText("Brutto: " + (int)((SumOfHours/60)*person.getSalaryPerHour()));
//        SalaryNetto.setText("");
//        float tax = 1 - (float)person.getTax()/100;
////        System.out.println("PersonTax: " + person.getTax() + "\n tax: " + tax);
//        SalaryNetto.setText("Netto: " +  (int)((SumOfHours/60)*person.getSalaryPerHour()*tax));
//        taxText.setText("");
//        taxText.setText("Tax: " + person.getTax()+"%");

//        System.out.println("WorkTime data getMonthValue: " + workTimeList.get(0).getDate().getMonthValue());
//        System.out.println("WorkTime work hours: " + hours + "h " + min + "min");
//        System.out.println("WorkTime start time: " + workTimeList.get(0).getStart_time() + " | end time: "+ workTimeList.get(0).getEnd_time());
    }

//    public void addNewPane(Pane pane){
//        workTimeDataPanel.getChildren().add(pane);
//    }


    public void changePass() throws SQLException {
        LoginPanelController loginPanelController = new LoginPanelController();
        String oldPassword = loginPanelController.getMd5(oldPass.getText());
        String newPassword = loginPanelController.getMd5(newPass.getText());
        Person person = LoginPanelController.getPersonData();
        LoadPersonData loadPersonData = new LoadPersonData();
        if(person.getPassword().equals(oldPassword) && !oldPass.getText().equals(newPass.getText())){
            loadPersonData.changePassword(newPassword, person.getId());
            System.out.println("Password changed!");
        }else{
            System.out.println("Wrong old password!");
        }

    }

//    public void clearPane(){
//        workTimeDataPanel.getChildren().clear();
//    }


//    public void clearDataPicker(){
//        LocalDate date = LocalDate.of(0001, 01, 01);
//        dateTime.setValue(date);
//    }



}


