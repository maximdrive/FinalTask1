package com.dao;


import com.beans.User;
import com.dao.exeption.DaoExeption;

import java.util.ArrayList;

public interface UserDao {
    int signIn(String login,String password) throws DaoExeption;
    void registration(User user) throws DaoExeption;
    ArrayList<User> showUsers();
    String getUserById(int id) throws DaoExeption;
}
