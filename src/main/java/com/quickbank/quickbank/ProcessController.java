package com.quickbank.quickbank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
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
    private StackPane stackPane;










    public void initialize(URL url, ResourceBundle resourceBundle){
    File btnLogoService = new File("images/icons8-service.png");
    File btnCredit = new File("images/icons8-credit-60.png");
    File btnStatistics = new File("images/icons8-statistics.png");
    File btnCard = new File("images/icons8-card-64.png");

}


    public void BtnServiceOnAction(ActionEvent event){
        System.out.println("Service button working!");
    }

    public void BtnCreditOnAction(ActionEvent event){
        System.out.println("Credit button working!");
    }

    public void BtnCardOnAction(ActionEvent event){
        System.out.println("Card button working!");
    }
    public void BtnAccountdOnAction(ActionEvent event){
        System.out.println("Account button working!");
    }

    public void BtnStatisticsOnAction(ActionEvent event){
        System.out.println("Statistics button working!");
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
            Scene scene = new Scene(fxmlLoader.load(), 559, 733);
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
