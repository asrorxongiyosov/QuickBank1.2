package com.quickbank.quickbank;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AccountController implements Initializable {

    public TextField TextFieldBirthDate;
    public TextField TextFieldBankBranch;
    public TextField TextFieldUsername;
    public TextField TextFieldBankSection;
    public TextField TextFieldPosition;
    public TextField TextFieldPassword;


    public Button btnEdit;
    public Button btnSave;

    public void Editable(boolean bool) {
        TextFieldBirthDate.setEditable(bool);
        TextFieldBankBranch.setEditable(bool);
        TextFieldUsername.setEditable(false);
        TextFieldBankSection.setEditable(bool);
        TextFieldPosition.setEditable(false);
        TextFieldPassword.setEditable(bool);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Editable(false);
    }

    public void about(String admin) throws SQLException {
        DatabaseConnection connectionNow = new DatabaseConnection();
        Connection connectDB = connectionNow.getConnection();
        String verifyLogin = "SELECT user.username ,user.birth_date,user.bank_branch,user.bank_section,user.password ,roles.name FROM user INNER JOIN roles ON roles.id=user.role_id WHERE username ='" + admin + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while (queryResult.next()) {
                String username = queryResult.getString("username");
                TextFieldUsername.setText(username);

                String birth_date = queryResult.getString("birth_date");
                TextFieldBirthDate.setText(birth_date);

                String bank_branch = queryResult.getString("bank_branch");
                TextFieldBankBranch.setText(bank_branch);

                String bank_section = queryResult.getString("bank_section");
                TextFieldBankSection.setText(bank_section);

                String role_id = queryResult.getString("roles.name");
                TextFieldPosition.setText(role_id);

                String password = queryResult.getString("password");
                TextFieldPassword.setText(password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnedit(ActionEvent event) {
        Editable(true);
    }

    public void btnSaveOnAction(ActionEvent event) throws SQLException {
        DatabaseConnection connectionNow = new DatabaseConnection();
        Connection connectDB = connectionNow.getConnection();
        String saveValue = "UPDATE user set birth_date='" + TextFieldBirthDate.getText() + "',bank_branch='" + TextFieldBankBranch.getText() + "',bank_section='" + TextFieldBankSection.getText() + "',password='" + TextFieldPassword.getText() + "' WHERE username='" + TextFieldUsername.getText() + "';";
        try {
            Statement statement = connectDB.createStatement();
            if (statement.executeUpdate(saveValue) == 1) {
                System.out.println("Success!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}