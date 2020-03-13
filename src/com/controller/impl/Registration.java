package com.controller.impl;

import com.beans.User;
import com.controller.command.Command;
import com.controller.exception.ParseException;
import com.servise.UserService;
import com.servise.exception.ServiceException;
import com.servise.factory.ServiceFactory;
import com.view.StartProcess;

import java.util.ArrayList;

public class Registration implements Command {
    @Override
    public String execute(String request) {
        try {
            String delimiter = "!!";

            String responce;

            String data = request.split(" ", 2)[1];
            String[] parsedData = data.split(delimiter);
            if (parsedData.length != 5) throw new ParseException("Wrong data");
            User user = new User();
            user.setLogin(parsedData[0]);
            user.setPassword(parsedData[1]);
            user.setName(parsedData[2]);
            user.setPassNumber(parsedData[3]);
            user.setPhone(parsedData[4]);

            ServiceFactory serviceFactoryObj = ServiceFactory.getInstance();
            UserService userService = serviceFactoryObj.getUserService();

            try {
                userService.registration(user);
                responce = "Success";
            } catch (ServiceException serExc) {
                responce = "Enter all information";
            }
            return responce;
        } catch (ParseException e) {
            return e.getMessage();
        }
    }
}
