package com.servise;

import com.beans.User;
import com.dao.exeption.DaoExeption;
import com.servise.exception.ServiceException;

import java.util.ArrayList;

public interface UserService {
    int signIn(String login,String password) throws ServiceException;
    void signOut(String login);
    void registration(User user) throws ServiceException;
    ArrayList<User> showUser() throws ServiceException;
}
