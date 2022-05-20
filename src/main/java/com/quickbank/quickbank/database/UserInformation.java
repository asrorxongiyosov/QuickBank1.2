package com.quickbank.quickbank.database;

import java.util.Date;

public class UserInformation {

    int user_id;
    String firstname;
    String lastname;
    String username;
    String gender;
    String bank_branch;
    String bank_section;
    String birth_date;
    String password;
    int role_id;
    int bank_id;
    Date created_date;

    public UserInformation(int user_id, String firstname, String lastname, String username, String gender, String bank_branch, String bank_section, String birth_date, String password, int role_id, int bank_id, Date created_date) {
        this.user_id = user_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.gender = gender;
        this.bank_branch = bank_branch;
        this.bank_section = bank_section;
        this.birth_date = birth_date;
        this.password = password;
        this.role_id = role_id;
        this.bank_id = bank_id;
        this.created_date = created_date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBank_branch() {
        return bank_branch;
    }

    public void setBank_branch(String bank_branch) {
        this.bank_branch = bank_branch;
    }

    public String getBank_section() {
        return bank_section;
    }

    public void setBank_section(String bank_section) {
        this.bank_section = bank_section;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getBank_id() {
        return bank_id;
    }

    public void setBank_id(int bank_id) {
        this.bank_id = bank_id;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }
}
