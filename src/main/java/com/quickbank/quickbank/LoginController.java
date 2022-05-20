package com.quickbank.quickbank;

import com.quickbank.quickbank.DatabaseConnection;
import com.quickbank.quickbank.database.UserInformation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;

import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Button cancelButton ;
    @FXML
    private ImageView brandingImageView;

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;
    @FXML
    private Label loginMessageLabel;

    @FXML
    private Button registerButton;

    @FXML
    private Button loginButton;

    @FXML
    private VBox mainVbox;


    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();

    }

    public void initialize(URL url, ResourceBundle resourceBundle){
//        mainVbox.setStyle("-fx-background-image: url(background.jpg)");
        mainVbox.setStyle("-fx-background-color: #716F81");
    }


    public void validateLogin() throws IOException {
        DatabaseConnection db = new DatabaseConnection();
        db.getUserInformation();

        ArrayList<UserInformation> usersInformation = db.getUsersInformation();

        boolean isLogged = true;

        for (UserInformation userInformation: usersInformation) {
            if (usernameTextField.getText().isBlank() || enterPasswordField.getText().isBlank()) {
                loginMessageLabel.setText("Please enter Username and Password");
            }
            if (userInformation.getUsername().equals(usernameTextField.getText()) && userInformation.getPassword().equals(enterPasswordField.getText())) {

                if (userInformation.getRole_id() == 1) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
                    Parent root = loader.load();

                    loginButton.getScene().getWindow().hide();
                    Stage login = new Stage();
                    Scene scene = new Scene(root);
                    login.setScene(scene);
                    login.show();
                } else if (userInformation.getRole_id() == 2) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("process.fxml"));
                    Parent root = loader.load();

                    loginButton.getScene().getWindow().hide();
                    Stage login = new Stage();
                    Scene scene = new Scene(root);
                    login.setMaximized(true);
                    login.setResizable(false);
                    login.setScene(scene);
                    login.show();
                }
            } else {
                isLogged = false;
            }
        }

        if(!isLogged) {
            loginMessageLabel.setText("You try to login");
        }
    }


//    public void validateLogin(ActionEvent event) throws IOException {
//
//        loginMessageLabel.setText("You try to login");
//        if (usernameTextField.getText().isBlank() || enterPasswordField.getText().isBlank()) {
//            loginMessageLabel.setText("Please enter Username and Password");
//        } else {
//            validateLogin();
//        }
//    }

    public void registerButtonOnAction(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("register.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 559, 733);
            Stage regstage = new Stage();
            regstage.initModality(Modality.APPLICATION_MODAL);
            regstage.initStyle(StageStyle.UNDECORATED);
            regstage.setTitle("Registration Page");
            regstage.setResizable(false);
            regstage.setScene(scene);
            regstage.show();

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


}