package com.servise.impl;

import com.beans.User;
import com.dao.UserDao;
import com.dao.exeption.DaoExeption;
import com.dao.factory.DaoFactory;
import com.servise.UserService;
import com.servise.exception.ServiceException;

import java.util.ArrayList;

public class UserServiceImpl implements UserService {

    @Override
    public int signIn(String login,String password) throws ServiceException {
        if(login == null || login.isEmpty())
            throw new ServiceException("Incorrect login");
        int id;
        try{
        DaoFactory daoObjFactory = DaoFactory.getInstance();
        UserDao userDao = daoObjFactory.getUserDao();

        id = userDao.signIn(login, password);
        }
        catch(DaoExeption e){
            throw new ServiceException(e);
        }
        return id;
    }

    @Override
    public void signOut(String login) {

    }

    @Override
    public void registration(User user) throws ServiceException{
        if(user.getLogin().isEmpty() || user.getPassword().isEmpty() || user.getPassNumber().isEmpty())
            throw new ServiceException("Login or password or passport number is empty");
        try{
            DaoFactory daoObjFactory = DaoFactory.getInstance();
            UserDao userDao = daoObjFactory.getUserDao();

            userDao.registration(user);

        } catch (DaoExeption daoExeption) {
            throw new ServiceException((daoExeption));
        }
    }

    @Override
    public ArrayList<User> showUser() throws ServiceException {
        ArrayList<User> users;

        DaoFactory daoFactoryObj = DaoFactory.getInstance();
        UserDao userDao = daoFactoryObj.getUserDao();
        users = userDao.showUsers();

        if(users.isEmpty())
            throw new ServiceException("No users in DB");

        return users;
    }
}
