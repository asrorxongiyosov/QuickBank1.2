package com.quickbank.quickbank;

import com.quickbank.quickbank.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
        DatabaseConnection connectionNow = new DatabaseConnection();
        Connection connectDB = connectionNow.getConnection();
        String verifyLogin = "SELECT count(1) FROM user WHERE username = '"+ usernameTextField.getText() +"' AND password ='"+ enterPasswordField.getText() + "'";



        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);


            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    loginMessageLabel.setText("Congrulations!");
                    Stage stage = (Stage) cancelButton.getScene().getWindow();
                    stage.close();
                    FXMLLoader fxmlLoader2 = new FXMLLoader(Main.class.getResource("process.fxml"));
                    Scene scene = new Scene(fxmlLoader2.load(), 559, 733);
//                    ProcessController processController = fxmlLoader2.getController();
//                    processController.setUserName();
                    Stage regstage = new Stage();
                    regstage.setMaximized(true);
                    regstage.setResizable(false);
                    regstage.setScene(scene);
                    regstage.show();
                }else{
                    loginMessageLabel.setText("Invalid Login. Please Try again.");
                }

            }

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }


    public void validateLogin(ActionEvent event) throws IOException {

        loginMessageLabel.setText("You try to login");
        if (usernameTextField.getText().isBlank() || enterPasswordField.getText().isBlank()) {
            loginMessageLabel.setText("Please enter Username and Password");
        } else {
            validateLogin();
        }
    }

    public void registerButtonOnAction(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("register.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 559, 733);
            Stage regstage = new Stage();
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