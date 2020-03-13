package com.controller.impl;

import com.Main;
import com.beans.User;
import com.controller.command.Command;
import com.servise.UserService;
import com.servise.exception.ServiceException;
import com.servise.factory.ServiceFactory;
import com.view.StartProcess;

import java.util.ArrayList;

public class ShowUsers implements Command {
    @Override
    public String execute(String request) {
        try {
            ArrayList<User> users;
            String responce = "";

            ServiceFactory serviceFactoryObj = ServiceFactory.getInstance();
            UserService userService = serviceFactoryObj.getUserService();

            try {
                users = userService.showUser();
                for (User user : users) {
                    responce += user.getIdUser() + "!!" + user.getLogin() + "!!" + user.getPassword() + "!!"
                            + user.getName() + "!!" + user.getPassNumber() + "!!" + user.getPhone() + "\n";
                }
                return responce;

            } catch (ServiceException e) {
                e.printStackTrace();
                responce = e.getMessage();
            }
            return responce;
        }catch (Exception e){
            return e.getLocalizedMessage();
        }
    }
}
