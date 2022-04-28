package com.quickbank.quickbank;


import javafx.application.Platform;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {


    @FXML
    private RadioButton genderMale;
    @FXML
    private RadioButton genderFemale;

    @FXML
    private ToggleGroup tggender;

    public String actionPerformed(ActionEvent ee)
    {
        if(genderMale.isSelected())
        {
            return (genderMale.getText()); //Male
        }
        else
        {
            return(genderFemale.getText()); //Female
        }
    }




        @FXML
        private ComboBox<String> branchbox;
        ObservableList<String> branch_list = FXCollections.observableArrayList("Tashkent","Andijan","Fergana","Namangan","Samarkand","Jizzakh","Navoi","Khorezm");
        public void BranchboxOnAction(ActionEvent event){

            branchbox.getPromptText();
            System.out.println(branchbox);

            System.out.println();
        }
        @FXML
        private ComboBox<String> sectionbox;
        ObservableList<String> section_list = FXCollections.observableArrayList("Service","Retail Banking","Corporate","Commercial Banking","Global Banking","Private Banking","Investment Banking");
        public void SectionBoxxOnAction(ActionEvent event){

        sectionbox.getPromptText();
        System.out.println(sectionbox);

    }

    @FXML private ComboBox<String> rolebox;
        ObservableList<String> role_list = FXCollections.observableArrayList("Manager","Operator","Customer");

        private void role_value(){
            switch(rolebox.getSelectionModel().getSelectedItem())
            {
                case "Manager":

                    break;
                case "Operator":
                    break;
                case "Customer":
                    break;
            }
        }




        @FXML
        private DatePicker brthdatebox;
        @FXML
        private CheckBox trmsfsbox;

        @FXML
        private ImageView shieldImageView;
        @FXML
        private Button closeButton;
        @FXML
        private Label alertMessage;
        @FXML
        private PasswordField setPasswordField;
        @FXML
        private PasswordField confirmPasswordField;
        @FXML
        private Label confirmPasswordLabel;

        @FXML
        private TextField firstnameTextfield;
        @FXML
        private TextField lastnameTextfield;
        @FXML
        private TextField usernameTextfield;


        public void initialize(URL url, ResourceBundle resourceBundle) {
            File shieldFile = new File("images/shield.png");
            Image shieldImage = new Image(shieldFile.toURI().toString());
            shieldImageView.setImage(shieldImage);

//          Radio buttons
            genderMale.setUserData("male");
            genderFemale.setUserData("female");
//          Combobox
            sectionbox.setItems(section_list);
            branchbox.setItems(branch_list);
            rolebox.setItems(role_list);


        }

        public void closeButtonOnAction(ActionEvent event){
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
            Platform.exit();
        }

        public boolean checkUserName() throws SQLException {
            boolean checker = false;
            DatabaseConnection connectionNow = new DatabaseConnection();
            Connection connectDB = connectionNow.getConnection();
            Statement statement = connectDB.createStatement();
            String usernameCheck = "SELECT username FROM user";
            ResultSet resultSet = statement.executeQuery(usernameCheck);
            while (resultSet.next()){
                String username = resultSet.getString("username");

                if((usernameTextfield.getText().equals(username))){
                    checker = true;
                    break;
                }
            }
            return checker;
        }

        public void registerButtonOnAction(ActionEvent event) throws SQLException {
//username checker

            if (checkUserName()){
                alertMessage.setText("Username is already exist");
            } else{
                if (setPasswordField.getText().equals(confirmPasswordField.getText())) {
                    registerUser();
                    confirmPasswordLabel.setText("");
                } else {
                    confirmPasswordLabel.setText("Password does not match! ");
                }
            }




        }

        public void registerUser() {
            DatabaseConnection connectionNow = new DatabaseConnection();
            Connection connectDB = connectionNow.getConnection();

            String firstname = firstnameTextfield.getText();
            String lastname = lastnameTextfield.getText();
            String username = usernameTextfield.getText();

            RadioButton selectedRadioButton = (RadioButton) tggender.getSelectedToggle();
            String gender = selectedRadioButton.getText();


            String birth_date = String.valueOf(brthdatebox.getValue());
            String bank_branch = String.valueOf( branchbox.getValue());
            String bank_section = String.valueOf(sectionbox.getValue());
            String password = setPasswordField.getText();

            LocalDate currentDate = LocalDate.now();
            int id = 0;
            switch(rolebox.getSelectionModel().getSelectedItem())
            {
                case "Manager":
                        id = 1;
                    break;
                case "Operator":
                    id = 2;
                    break;
                case "Customer":
                    id = 3;
                    break;
            }
            String insertFields = "INSERT INTO user( firstname, lastname, username, gender,bank_branch,bank_section,birth_date, password, role_id,bank_id,created_date) VALUES('";
            String insertValues = firstname + "','" + lastname + "','" + username + "','" + gender +  "','" + bank_branch + "','" + bank_section + "','" + birth_date + "','" + password + "','" + String.valueOf(id) + "','" + 1 +"','" + currentDate +  "')";
            String insertToRegister = insertFields + insertValues;

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insertToRegister);
                alertMessage.setText("User has been registered successfully!");

            }catch (Exception e){
                e.printStackTrace();
                e.getCause();
            }



        }

        public void deleteUser(){
            DatabaseConnection connectionNow = new DatabaseConnection();
            Connection connectDB = connectionNow.getConnection();


        }


//
//            String adduser = "INSERT INTO user(firstname,lastname,username,gender,birth_date,password,created_date) VALUES('"firstnameTextfield.getText()"')";
//
//            try{
//                Statement statement = connectDB.createStatement();
//                ResultSet queryresult = statement.executeQuery(adduser);
//            }







    }













