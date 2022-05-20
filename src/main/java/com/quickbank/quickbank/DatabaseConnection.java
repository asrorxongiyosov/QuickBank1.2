package com.quickbank.quickbank;

import com.quickbank.quickbank.database.UserInformation;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName = "QuickBank";
        String databaseUser = "root";
        String databasePassword = "1234";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;
    }
  ArrayList<UserInformation> usersInformation = new ArrayList<>();

    public void getUserInformation(){
        try{
            ResultSet rs;
            PreparedStatement ps;
            Connection conn;

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/QuickBank", "root", "1234");
            ps = conn.prepareStatement("SELECT * FROM user");
            rs = ps.executeQuery();
            while(rs.next()){
                int user_id = rs.getInt("user_id");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String username = rs.getString("username");
                String gender = rs.getString("gender");
                String bank_branch = rs.getString("bank_branch");
                String bank_section = rs.getString("bank_section");
                String birth_date = rs.getString("birth_date");
                String password = rs.getString("password");
                int role_id = rs.getInt("role_id");
                int bank_id = rs.getInt("bank_id");
                Date created_date = rs.getDate("created_date");

                usersInformation.add(new UserInformation(user_id,firstname,lastname,username,gender,bank_branch,bank_section,birth_date,password,role_id,bank_id,created_date));
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public ArrayList<UserInformation> getUsersInformation(){
        return usersInformation;
    }
}
