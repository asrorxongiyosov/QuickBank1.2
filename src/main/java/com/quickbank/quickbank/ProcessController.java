package com.quickbank.quickbank;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProcessController implements Initializable {

    @FXML
    private Button btnService;
    @FXML
    private Button btnCredit;
    @FXML
    private Button btnCard;
    @FXML
    private Button btnAccount;
    @FXML
    private Button btnStatistics;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnExit;
    @FXML
    private StackPane contentArea;
    @FXML
    public Label userInfo;
    public String admin;
    @FXML
    private Button btnNewUser;

    @FXML
    public void BtnNewUser() throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("newUser.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);

    }


    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }












    public void initialize(URL url, ResourceBundle resourceBundle){
        File btnLogoService = new File("images/icons8-service.png");
        File btnCredit = new File("images/icons8-credit-60.png");
        File btnStatistics = new File("images/icons8-statistics.png");
        File btnCard = new File("images/icons8-card-64.png");

    }


//    Button Actions

   public void BtnExitOnAction(ActionEvent event){
       Stage stage = (Stage) btnExit.getScene().getWindow();
       stage.close();
       Platform.exit();
   }

    public void BtnCreditOnAction(ActionEvent event) throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("credit.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    public void BtnCardOnAction(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("card.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
    public void BtnAccountdOnAction(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("account.fxml"));
        Parent root = loader.load();
        AccountController controlAccount = loader.getController();
        controlAccount.about(getAdmin());
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }

    public void userInfo(String firstname, String lastname){

        userInfo.setText(firstname + " " + lastname);

    }

    public void BtnStatisticsOnAction(ActionEvent event) throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("statistics.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    double x,y;
    public void workStation(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("process.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);


        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged(event ->{
            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);
        } );
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("process.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage regstage = new Stage();
            regstage.setTitle("Quick Pay");



            regstage.setScene(scene);
            regstage.show();

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }


    }

}
