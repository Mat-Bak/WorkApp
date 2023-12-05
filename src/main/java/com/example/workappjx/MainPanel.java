package com.example.workappjx;

import javafx.concurrent.Worker;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

import java.math.BigInteger;
import java.net.URL;
import java.sql.*;
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
    public Pane workersPane;

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
    public VBox workersVBox;

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

    @FXML
    public AnchorPane anchorPane;
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

    public int personID;

    public static int workTimeID;

//    public Image binImage = new Image("E:\\Nauka\\Reposytory\\WorkApp\\src\\main\\resources\\com\\example\\workappjx\\images\\bin.png");
    public Image binImage = new Image("src\\main\\resources\\com\\example\\workappjx\\images\\bin.png");


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

    public void showWorkersPanel() throws SQLException {
        mainPanel.setVisible(false);
        workersPane.setVisible(true);
        workersVBox.getChildren().clear();
        showWorkersList();
    }

    public void workersPanelBack(){
        mainPanel.setVisible(true);
        workersPane.setVisible(false);
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

    public void showWorkersList(){
        System.out.println("TEST CZY TO GÓWNO DZIAŁA!");
        DatabaseConnector connector = new DatabaseConnector();

        int id;
        String login;
        String password = null;
        String firstName = null;
        String lastName = null;
        BigInteger pesel = null;
        int phoneNumber = 0;
        int SalaryPerHour = 0;
        boolean admin = false;
//        Person user = null;

        List<Person> personList = new ArrayList<>();

        // SQL query
//        String query = "SELECT * FROM workers";
        String query = "SELECT * FROM Persons.workers;";


        // Execute query and get result
        ResultSet resultSet = null;
        try {
            resultSet = connector.executeQuery(query);
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                login = resultSet.getString("login");
                password = resultSet.getString("password");
                firstName = resultSet.getString("firstName");
                lastName = resultSet.getString("lastName");
                phoneNumber = resultSet.getInt("phoneNumber");
                pesel = BigInteger.valueOf(resultSet.getLong("pesel"));
                SalaryPerHour = resultSet.getInt("SalaryPerHour");
                admin = resultSet.getBoolean("admin");

                Person person = new Person(id, login, password, firstName, lastName, pesel, phoneNumber, SalaryPerHour, admin);
//                Person person = new Person(id, login, password, firstName, lastName, pesel, phoneNumber);
                personList.add(person);
            }
            resultSet.close();
            connector.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        for(Person person : personList){
            personID = person.getId();
            Text name = new Text(person.getFirstName() + " " + person.getLastName());
//            Text time = new Text(workTime.timeToStrikg());
            name.setTextAlignment(TextAlignment.CENTER);
//            time.setTextAlignment(TextAlignment.CENTER);

            TextFlow namePane = new TextFlow();
            TextFlow buttonPane = new TextFlow();
//            TextFlow timePane = new TextFlow();
//
            ImageView view = new ImageView(binImage);
            view.setFitHeight(15);
            view.setPreserveRatio(true);

            Button deleteWorker = new Button();
            deleteWorker.setGraphic(view);
            deleteWorker.setPrefSize(10,10);
            buttonPane.getChildren().add(deleteWorker);
            buttonPane.setTextAlignment(TextAlignment.CENTER);
            buttonPane.setPadding(new Insets(5,5,5,5));
            namePane.setPrefSize(25,39);
            namePane.setTextAlignment(TextAlignment.CENTER);
            namePane.setPrefSize(400, 39);
//            namePane.setLayoutX(25);
            namePane.getChildren().add(name);
            namePane.setLayoutX(0);
            namePane.setPadding(new Insets(5,5,5,5));



//
//            timePane.setTextAlignment(TextAlignment.CENTER);
//            timePane.setPrefSize(94, 39);
////            timePane.getChildren().add(time);
//            timePane.setLayoutX(285);
//            timePane.setPadding(new Insets(5,5,5,5));

            Pane pane = new Pane();
            pane.setPrefWidth(426);
            pane.setPrefHeight(40);
            pane.getChildren().addAll(namePane, buttonPane);

            pane.setStyle("-fx-border-color: grey;" +
                    "-fx-border-style: solid none solid none;" +
                    "-fx-border-width: 2;" +
                    "-fx-background-color: lightgrey;");

//            dataPane.setStyle("-fx-border-color: grey;" +
//                    "-fx-border-style: hidden solid hidden hidden;" +
//                    "-fx-border-width: 4;");
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

            String finalFirstName = firstName;
            namePane.setOnMouseClicked(event -> {

//                String userName;
//                String userSurname;
//                int userPhone;
//                BigInteger userPesel;
//                int userSalary;
//                boolean userAdmin;
//                DatabaseConnector databaseConnector = new DatabaseConnector();
//
//                String getUser = "SELECT * FROM Persons.address WHERE id = '" + person.getId() + "';";
//                ResultSet res = null;
//
//                try {
//                    res = databaseConnector.executeQuery(getUser);
//                    userName = res.getString("firstName");
//                    userSurname = res.getString("lastName");
//                    userPhone = res.getInt("phoneNumber");
//                    userPesel = new BigInteger(String.valueOf(res.getInt("pesel")));
//                    userSalary = res.getInt("SalaryPerHour");
//                    userAdmin = res.getBoolean("admin");
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }





                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("workerPanel.fxml"));
                Scene secondScene = null;
                try {
                    secondScene = new Scene(fxmlLoader.load(), 326, 425);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Stage Secondstage = new Stage();
                Secondstage.setScene(secondScene);
                Secondstage.show();
                WorkerPanel workerPanel = fxmlLoader.getController();
                workerPanel.setPersonID(person.getId());
                workerPanel.setDataInPanel(person.getFirstName(), person.getLastName(), person.getPesel(), person.getPhoneNumber(), person.getSalaryPerHour(), person.getAdmin());
//                workersVBox.getChildren().clear();
//                anchorPane.setVisible(false);
                workersPanelBack();
            });

            deleteWorker.setOnMouseClicked(event -> {
                String url = "jdbc:mysql://localhost/persons";
                String username = "root";
                String passwordDb = "1234qwer";

                try {
                    Connection connection = DriverManager.getConnection(url, username, passwordDb);

                    System.out.println("second id workTime: " + person.getId());
                    String sql = "DELETE FROM persons.workers WHERE id = '" + person.getId() + "';";

                    PreparedStatement statement = connection.prepareStatement(sql);


                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
//                System.out.println("Dane zostały dodane do bazy.");
                    }

                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                workersVBox.getChildren().clear();
                showWorkersList();

            });


            workersVBox.getChildren().add(pane);

        }




    }

    public void clearWorkersPanel(){
        workersVBox.getChildren().clear();
    }

    public void showAddNewWorkerPanel() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("newWorkerPanel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 462, 521);
        Stage stage = new Stage();
        //E:/Nauka/Java/workApp/workAppJX/src/main/resources/
        stage.setTitle("Add New Worker");
        stage.setScene(scene);
        stage.show();
    }

//    public void setUserData(String name, String surname, int phone, BigInteger pesel, int salary, boolean admin){
//        this.person.setFirstName(name);
//        this.person.setLastName(surname);
//        this.person.setPhoneNumber(phone);
//        this.person.setPesel(pesel);
//        this.person.setSalaryPerHour(salary);
//        this.person.setAdmin(admin);
//    }

//    public void clearPane(){
//        workTimeDataPanel.getChildren().clear();
//    }


//    public void clearDataPicker(){
//        LocalDate date = LocalDate.of(0001, 01, 01);
//        dateTime.setValue(date);
//    }



}


