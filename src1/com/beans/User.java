package com.beans;

public class User {
    private int idUser;
    private String login;
    private String password;
    private String passNumber;
    private String phone;
    private String name;

    public User(String login,String password, String passNumber, String phone, String name){
        this.login = login;
        this.password = password;
        this.passNumber = passNumber;
        this.phone = phone;
        this.name = name;
    }
    public User(){};

    public int getIdUser() {
        return idUser;
    }

    public String getLogin() {
        return login;
    }

    public String getPassNumber() {
        return passNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassNumber(String passNumber) {
        this.passNumber = passNumber;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

