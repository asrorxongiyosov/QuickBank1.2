package com.quickbank.quickbank;

import com.quickbank.quickbank.DatabaseConnection;
import javafx.application.Platform;
import javafx.beans.Observable;
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
            String branchbox = String.valueOf(branch_list);
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
        public void RoleBoxOnAction(ActionEvent event){


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
        private Label registrationMessage;
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

        public void registerButtonOnAction(ActionEvent event){

            if (setPasswordField.getText().equals(confirmPasswordField.getText())) {
                registerUser();
                confirmPasswordLabel.setText("");


            } else {
                confirmPasswordLabel.setText("Password does not match! ");
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
            String password = setPasswordField.getText();

            LocalDate currentDate = LocalDate.now();

            String insertFields = "INSERT INTO user( firstname, lastname, username, gender,birth_date, password, created_date) VALUES('";
            String insertValues = firstname + "','" + lastname + "','" + username + "','" + gender +  "','" + birth_date + "','" + password + "','" + currentDate +  "')";
            String insertToRegister = insertFields + insertValues;

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insertToRegister);
                registrationMessage.setText("User has been registered successfully!");

            }catch (Exception e){
                e.printStackTrace();
                e.getCause();
            }



        }


//
//            String adduser = "INSERT INTO user(firstname,lastname,username,gender,birth_date,password,created_date) VALUES('"firstnameTextfield.getText()"')";
//
//            try{
//                Statement statement = connectDB.createStatement();
//                ResultSet queryresult = statement.executeQuery(adduser);
//            }







    }













