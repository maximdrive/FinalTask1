package com.dao.impl;

import com.beans.User;
import com.dao.UserDao;
import com.dao.exeption.DaoExeption;
import com.dao.handler.Handler;

import java.sql.*;
import java.util.ArrayList;

public class SqlUserDao implements UserDao {
    Connection connection;
    Statement st;
    ResultSet rs;

    @Override
    public int signIn(String login, String password) throws DaoExeption {
        connection = Handler.getDbConnection();
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT id FROM user WHERE Login = '" + login + "' AND Passw = '" + password +"'");
            if(!rs.first()) throw new DaoExeption("Incorrect login or password");
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void registration(User user) throws DaoExeption{
        if(!isLoginUnique(user.getLogin()))
            throw new DaoExeption("Login is not unique");

        connection = Handler.getDbConnection();
        int id;
        try{
            id = Handler.getIdFromDB("user");
            user.setIdUser(id);

            String querry = "INSERT INTO user  VALUES (?,?,?)";
            PreparedStatement st = connection.prepareStatement(querry);
            st.setString(2,user.getLogin());
            st.setString(3,user.getPassword());
            st.setInt(1,id);
            st.executeUpdate();

            querry = "INSERT INTO person VALUES (?,?,?,?)";
            st = connection.prepareStatement(querry);
            st.setString(1,user.getName());
            st.setString(2,user.getPassNumber());
            st.setString(3,user.getPhone());
            st.setInt(4,id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ArrayList<User> showUsers() {
        connection = Handler.getDbConnection();
        ArrayList<User> users = new ArrayList<>();
        User user;
        try{
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM user inner join person on user.id = person.id");
            while(rs.next()){
                user = new User();
                user.setIdUser(rs.getInt(1));
                user.setLogin(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setName(rs.getString(4));
                user.setPassNumber(rs.getString(5));
                user.setPhone(rs.getString(6));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isLoginUnique(String login){
        connection = Handler.getDbConnection();
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM user WHERE Login = '" + login + "'");
            if(rs.first()) return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public String getUserById(int id) throws DaoExeption {
        connection = Handler.getDbConnection();
        try{
            st = connection.createStatement();
            rs = st.executeQuery("select person.PName from user " +
                    "inner join person on user.id = person.id " +
                    "where user.id = " + id);
            if(!rs.first()) throw new DaoExeption("Wrong Id");
            return rs.getString(1);
        } catch (SQLException e) {
            return "Exeption";
        }
    }
}
